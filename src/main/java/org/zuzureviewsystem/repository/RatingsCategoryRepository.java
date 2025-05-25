package org.zuzureviewsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zuzureviewsystem.entity.ProviderEntity;
import org.zuzureviewsystem.entity.RatingCategoryEntity;

import java.util.List;

@Repository
public interface RatingsCategoryRepository extends JpaRepository<RatingCategoryEntity, Long> {

}
