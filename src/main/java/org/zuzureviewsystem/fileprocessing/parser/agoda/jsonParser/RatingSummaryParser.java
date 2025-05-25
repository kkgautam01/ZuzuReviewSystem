package org.zuzureviewsystem.fileprocessing.parser.agoda.jsonParser;

import com.fasterxml.jackson.databind.JsonNode;
import org.zuzureviewsystem.entity.ProviderEntity;
import org.zuzureviewsystem.filehandling.service.Provider;
import org.zuzureviewsystem.fileprocessing.dto.HotelDto;
import org.zuzureviewsystem.fileprocessing.dto.HotelReviewDto;
import org.zuzureviewsystem.fileprocessing.dto.RatingSummaryDto;

public class RatingSummaryParser {
    public static RatingSummaryDto parse(JsonNode node, Provider provider, HotelDto hotelDto){
        RatingSummaryDto ratingSummaryDto = new RatingSummaryDto();
        ratingSummaryDto.setHotelId(hotelDto.getHotelId());
        ratingSummaryDto.setProviderId(provider.getProviderId());
        ratingSummaryDto.setTotalRating(node.get("overallScore").asDouble());
        ratingSummaryDto.setReviewCount(node.get("reviewCount").asInt());

        return ratingSummaryDto;
    }
}
