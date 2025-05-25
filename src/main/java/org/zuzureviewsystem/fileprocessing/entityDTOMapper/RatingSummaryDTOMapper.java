package org.zuzureviewsystem.fileprocessing.entityDTOMapper;

import org.springframework.stereotype.Component;
import org.zuzureviewsystem.entity.HotelEntity;
import org.zuzureviewsystem.entity.RatingSummaryEntity;
import org.zuzureviewsystem.fileprocessing.dto.HotelDto;
import org.zuzureviewsystem.fileprocessing.dto.RatingSummaryDto;

@Component
public class RatingSummaryDTOMapper {
    public static RatingSummaryEntity mapToEntity(RatingSummaryDto ratingSummaryDto){
        RatingSummaryEntity ratingSummaryEntity = new RatingSummaryEntity();
        ratingSummaryEntity.setHotelId(ratingSummaryDto.getHotelId());
        ratingSummaryEntity.setProviderId(ratingSummaryDto.getProviderId());
        ratingSummaryEntity.setReviewCount(ratingSummaryDto.getReviewCount());
        ratingSummaryEntity.setTotalScore(ratingSummaryDto.getTotalRating());

        return ratingSummaryEntity;
    }
}
