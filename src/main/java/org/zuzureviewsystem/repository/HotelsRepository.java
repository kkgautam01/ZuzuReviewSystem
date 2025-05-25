package org.zuzureviewsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zuzureviewsystem.entity.HotelEntity;
import org.zuzureviewsystem.entity.ProviderEntity;

import java.util.List;

@Repository
public interface HotelsRepository extends JpaRepository<HotelEntity, Long> {

}
