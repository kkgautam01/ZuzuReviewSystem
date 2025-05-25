package org.zuzureviewsystem.fileprocessing.entityDTOMapper;

import org.springframework.stereotype.Component;
import org.zuzureviewsystem.entity.HotelEntity;
import org.zuzureviewsystem.fileprocessing.dto.HotelDto;

@Component
public class HotelDTOMapper {
    public static HotelEntity mapToEntity(HotelDto hotelDto){
        HotelEntity hotelEntity = new HotelEntity();
        hotelEntity.setName(hotelDto.getHotelName());
        hotelEntity.setHotelId(hotelDto.getHotelId());
        hotelEntity.setProviderId(hotelDto.getProviderId());


        return hotelEntity;
    }
}
