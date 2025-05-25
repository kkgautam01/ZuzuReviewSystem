package org.zuzureviewsystem.fileprocessing.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.zuzureviewsystem.entity.*;
import org.zuzureviewsystem.fileprocessing.dto.*;
import org.zuzureviewsystem.fileprocessing.entityDTOMapper.*;

import java.util.List;

@Component
public class EntityDTOMapping {

    public HotelEntity mapHotel(HotelDto hotelDto){
        return HotelDTOMapper.mapToEntity(hotelDto);
    }

    public ReviewEntity mapReview(ReviewDto reviewDto, Long reviewerId){
         return ReviewsDTOMapper.mapToEntity(reviewDto, reviewerId);
    }

    public ReviewerEntity mapReviewer(ReviewerDto reviewerDto){
        return ReviewerDTOMapper.mapToEntity(reviewerDto);
    }

    public RatingSummaryEntity mapRatingSummary(RatingSummaryDto ratingSummaryDto){
        return  RatingSummaryDTOMapper.mapToEntity(ratingSummaryDto);
    }

    public List<RatingsEntity> mapRatings(List<RatingsDto> ratingsListDto, RatingSummaryEntity ratingSummaryEntity){
        return RatingsDTOMapper.mapToEntity(ratingsListDto, ratingSummaryEntity);
    }

}
