package org.zuzureviewsystem.fileprocessing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zuzureviewsystem.entity.ProviderEntity;
import org.zuzureviewsystem.filehandling.service.Provider;
import org.zuzureviewsystem.fileprocessing.adapter.ProviderAdapter;
import org.zuzureviewsystem.fileprocessing.dto.HotelReviewDto;
import org.zuzureviewsystem.fileprocessing.factory.ProviderAdaptorFactory;
import org.zuzureviewsystem.fileprocessing.strategy.ProviderAdapterStrategy;
import org.zuzureviewsystem.util.LogMessages;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DataHandler {
    private static final Logger LOGGER = Logger.getLogger(DataHandler.class.getName());
    @Autowired
    StoreService storeService;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    ProviderAdaptorFactory providerAdaptorFactory;
    public boolean saveContent(String line, ProviderAdapter adapter, Provider provider, String fileType,
                               Map<String, Integer> languageIdMap, Map<String, Integer> ratingCategoryIdMap){

        HotelReviewDto hotelReviewDto = adapter.parse(line, provider, fileType, languageIdMap, ratingCategoryIdMap);
        if(hotelReviewDto != null){

            // check if log already processed
            if(!repositoryService.isLogExist(hotelReviewDto.getReviewDto().getHotelId(),
                    hotelReviewDto.getReviewDto().getProviderId(),
                    hotelReviewDto.getReviewDto().getHotelReviewId())) {

                return storeService.save(hotelReviewDto);
            }
        }else{
            LOGGER.log(Level.SEVERE, LogMessages.INVALID_JSON_FORMAT + " : Line : " + line);
        }
        return true;
    }
}
