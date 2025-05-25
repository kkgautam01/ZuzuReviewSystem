package org.zuzureviewsystem.filehandling.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuzureviewsystem.entity.FilesEntity;
import org.zuzureviewsystem.entity.LanguageEntity;
import org.zuzureviewsystem.entity.ProviderEntity;
import org.zuzureviewsystem.entity.RatingCategoryEntity;
import org.zuzureviewsystem.filehandling.cloud.Cloud;
import org.zuzureviewsystem.filehandling.factory.*;
import org.zuzureviewsystem.fileprocessing.service.FileProcessingService;
import org.zuzureviewsystem.fileprocessing.service.FileService;
import org.zuzureviewsystem.fileprocessing.service.RepositoryService;
import org.zuzureviewsystem.filehandling.strategy.FileFetchStrategy;
import org.zuzureviewsystem.util.LogMessages;
import org.zuzureviewsystem.util.Utilities;

import java.io.BufferedReader;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class FileHandling {
    @Autowired
    FileProcessingService fileProcessing;

    @Autowired
    Utilities util;
    @Autowired
    FileService fileService;
    @Autowired
    CloudConfigAdapterFactory cloudConfigFactory;
    @Autowired
    FileFetchSelectionFactory fileFactory;
    @Autowired
    private RepositoryService storeReviewsService;
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    ObjectMapper objectMapper = new ObjectMapper();

    public FileHandling() {}

    @PostConstruct
    public void process(){
        LOGGER.log(Level.INFO, LogMessages.REVIEW_PROCESS_START);
        // get providers data
        List<ProviderEntity> providers = storeReviewsService.getAllProviders();

        // Storing Reusable data in System Memory
        // NOTE: Redis can be used for real world systems

        // Create Language Map
        List<LanguageEntity> languagesList = storeReviewsService.getAllLanguages();
        Map<String, Integer> languageIdMap = util.getLanguageIdMap(languagesList);

        // Create Rating-Category Map
        List<RatingCategoryEntity> ratingCategoriesList = storeReviewsService.getAllRatingCategory();
        Map<String, Integer> ratingCategoryIdMap = util.getReviewCategoryIdMap(ratingCategoriesList);

        // Create thread Pool
        int threadPoolCount = util.createCpuBoundThreadPool();
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolCount);
        for(ProviderEntity entity : providers) {
            try{
                Provider provider = new Provider(entity);
                List<FilesEntity> fileList = storeReviewsService.getAllFiles();
                Map<String, Boolean> filesNameStatusMap = util.getFilesNameStatusMap(fileList);
                JsonNode jsonNode = objectMapper.readTree(provider.getCloudInfo());

                // get cloud Provider Name. Aws, Gcp, etc..
                String cloudType = util.getCloudType(jsonNode);

                // get appropriate cloud config
                Cloud cloudConfig = cloudConfigFactory.getConfig(cloudType,jsonNode,provider);

                // get file strategy
                FileFetchStrategy fileStrategy = fileFactory.getStrategy(cloudConfig);

                List<String> filesList = fileStrategy.fetchFileList();

                // get paginated list of files from cloud storage
                for(String file : filesList){

                    // Filter duplicate files
                    if(filesNameStatusMap.containsKey(file)) {
                        if(filesNameStatusMap.get(file))
                            continue;
                    }

                    boolean isFileProcessed = false;
                    // Read file from S3
                    try {
                        BufferedReader reader = fileStrategy.fetchFile(file);
                        isFileProcessed = true;
                        // Process file Async
                        executor.submit(() -> {
                            // Save files
                            fileProcessing.process(reader, provider, file,
                                    languageIdMap,
                                    ratingCategoryIdMap);
                        });

                    }catch( Exception e){
                        LOGGER.log(Level.SEVERE, LogMessages.COULD_NOT_PROCESS_THE_FILE + " :: " + e.getMessage());
                    }

                    // Add file status to the table
                    fileService.save(isFileProcessed, provider.getProviderId(),  file,
                            cloudConfig.getBucket());


                }
            }catch(Exception e){
                LOGGER.log(Level.SEVERE, LogMessages.INVALID_PROVIDER_CONFIG + " :: " + e.getMessage());
            }

        }
        executor.shutdown();

        LOGGER.log(Level.INFO, LogMessages.REVIEW_PROCESS_END);

    }
}
