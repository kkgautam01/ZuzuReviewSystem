package org.zuzureviewsystem.fileprocessing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuzureviewsystem.filehandling.service.Provider;
import org.zuzureviewsystem.fileprocessing.adapter.ProviderAdapter;
import org.zuzureviewsystem.fileprocessing.factory.ProviderAdaptorFactory;
import org.zuzureviewsystem.fileprocessing.strategy.ProviderAdapterStrategy;
import org.zuzureviewsystem.util.FileType;
import org.zuzureviewsystem.util.LogMessages;

import java.io.BufferedReader;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class FileProcessingService {
    @Autowired
    ProviderAdaptorFactory providerAdaptorFactory;
    @Autowired
    DataHandler dataHandler;


    @Autowired
    RetryHandler retryService;
    private static final Logger LOGGER = Logger.getLogger(FileProcessingService.class.getName());

    public void process(BufferedReader reader, Provider provider, String file,
                        Map<String, Integer> languageIdMap, Map<String, Integer> ratingCategoryIdMap){
        ProviderAdapterStrategy providerSelectionStrategy = providerAdaptorFactory.getStrategy(provider.getName());
        ProviderAdapter adapter = providerSelectionStrategy.getAdapter();

        readFile(reader, adapter, provider, file, languageIdMap, ratingCategoryIdMap);


    }

    public void readFile(BufferedReader reader, ProviderAdapter adapter,
                         Provider provider, String file, Map<String, Integer> languageIdMap,
                         Map<String, Integer> ratingCategoryIdMap){
        String line;
        try {
            String fileType = FileType.fromFileName(file);
            int retry =0;
            boolean isSaved;
            while ((line = reader.readLine()) != null) {
                isSaved = false;

                try {
                    isSaved = dataHandler.saveContent(line, adapter, provider,
                            fileType, languageIdMap, ratingCategoryIdMap);
                }catch (Exception ignored) {}

                if (!isSaved) {
                    retryService.retry(line, adapter, provider, file, fileType,
                            languageIdMap, ratingCategoryIdMap, retry);
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, LogMessages.COULD_NOT_PROCESS_THE_FILE + " : File : " + file);
        }
    }



}
