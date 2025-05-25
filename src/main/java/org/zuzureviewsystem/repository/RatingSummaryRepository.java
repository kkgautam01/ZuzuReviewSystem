package org.zuzureviewsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zuzureviewsystem.entity.HotelEntity;
import org.zuzureviewsystem.entity.RatingSummaryEntity;

@Repository
public interface RatingSummaryRepository extends JpaRepository<RatingSummaryEntity, Long> {

}
