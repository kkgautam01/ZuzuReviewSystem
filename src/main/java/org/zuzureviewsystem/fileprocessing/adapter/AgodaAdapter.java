package org.zuzureviewsystem.fileprocessing.adapter;

import org.springframework.stereotype.Component;
import org.zuzureviewsystem.entity.ProviderEntity;
import org.zuzureviewsystem.filehandling.service.Provider;
import org.zuzureviewsystem.fileprocessing.dto.HotelReviewDto;
import org.zuzureviewsystem.fileprocessing.parser.agoda.AgodaJsonParser;
import org.zuzureviewsystem.util.Constants;
import org.zuzureviewsystem.util.LogMessages;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class AgodaAdapter implements ProviderAdapter{
    private static final Logger LOGGER = Logger.getLogger(AgodaAdapter.class.getName());

    public HotelReviewDto parse(String line, Provider provider, String fileType, Map<String, Integer> languageIdMap, Map<String, Integer> ratingCategoryIdMap) {
        // Extend this method to support multiple file formats
        switch (fileType.toLowerCase()) {
            case Constants.JSON, Constants.JL -> {
                AgodaJsonParser agodaJsonParser = new AgodaJsonParser();
                return agodaJsonParser.parse(line, provider, languageIdMap, ratingCategoryIdMap);
            }
            default -> {
                LOGGER.log(Level.SEVERE, LogMessages.UNSUPPORTED_FILE_TYPE);
            }

        }
        return null;
    }

}
