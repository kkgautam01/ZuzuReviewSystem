package org.zuzureviewsystem.fileprocessing.entityDTOMapper;

import org.springframework.stereotype.Component;
import org.zuzureviewsystem.entity.HotelEntity;
import org.zuzureviewsystem.entity.RatingSummaryEntity;
import org.zuzureviewsystem.entity.RatingsEntity;
import org.zuzureviewsystem.fileprocessing.dto.HotelDto;
import org.zuzureviewsystem.fileprocessing.dto.RatingsDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class RatingsDTOMapper {
    public static List<RatingsEntity> mapToEntity(List<RatingsDto> ratingsListDto, RatingSummaryEntity ratingSummaryEntity){
        List<RatingsEntity> ratingsEntityList = new ArrayList<>();

        for(RatingsDto rating : ratingsListDto){
            RatingsEntity ratingsEntity = new RatingsEntity();
            ratingsEntity.setRatingSummaryId(ratingSummaryEntity.getId());
            ratingsEntity.setHotelId(ratingSummaryEntity.getHotelId());
            ratingsEntity.setProviderId(ratingSummaryEntity.getProviderId());
            ratingsEntity.setCategory(rating.getCategory());
            ratingsEntity.setRating(rating.getRating());
            ratingsEntityList.add(ratingsEntity);
        }


        return ratingsEntityList;
    }
}
