package org.zuzureviewsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.zuzureviewsystem.entity.FilesEntity;
import org.zuzureviewsystem.entity.HotelEntity;
import org.zuzureviewsystem.entity.ReviewEntity;

import java.util.List;

@Repository
public interface FilesRepository extends JpaRepository<FilesEntity, Long> {
    @Query(value = "SELECT * " +
            "FROM files WHERE provider_id = :providerId AND file = :file",
            nativeQuery = true)
    FilesEntity getFileByNameAndProviderId(@Param("file") String file,
                                                         @Param("providerId") Long providerId);

}
