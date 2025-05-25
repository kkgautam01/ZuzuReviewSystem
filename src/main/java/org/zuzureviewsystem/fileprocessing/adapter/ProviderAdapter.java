package org.zuzureviewsystem.fileprocessing.adapter;

import org.zuzureviewsystem.entity.ProviderEntity;
import org.zuzureviewsystem.filehandling.service.Provider;
import org.zuzureviewsystem.fileprocessing.dto.HotelReviewDto;

import java.util.Map;

public interface ProviderAdapter {
    HotelReviewDto parse(String line, Provider provider, String fileType, Map<String, Integer> languageIdMap, Map<String, Integer> ratingCategoryIdMap);

}
