package org.zuzureviewsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zuzureviewsystem.entity.LanguageEntity;
import org.zuzureviewsystem.entity.ProviderEntity;

import java.util.List;

@Repository
public interface LanguageRepository extends JpaRepository<LanguageEntity, Long> {

}
