package org.zuzureviewsystem.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.zuzureviewsystem.entity.FilesEntity;
import org.zuzureviewsystem.entity.LanguageEntity;
import org.zuzureviewsystem.entity.RatingCategoryEntity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Utilities {

    public String getCloudType(JsonNode jsonNode){
        return jsonNode.get("type").asText();
    }

    public JsonNode getFileAsJsonNode(String line) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = null;
        try {
            node = mapper.readTree(line);
        } catch (Exception e) {
            throw new RuntimeException("Invalid JSON format: " + e.getMessage());
        }

        return node;
    }

    // Create Map of Language <language,id>
    public Map<String, Integer> getLanguageIdMap(List<LanguageEntity> languageList) {
        return languageList.stream()
                .collect(Collectors.toMap(LanguageEntity::getLanguage, LanguageEntity::getId));
    }

    // Create Map of Rating Category <category,id>
    public Map<String, Integer> getReviewCategoryIdMap(List<RatingCategoryEntity> ratingCategoriesList) {
        return ratingCategoriesList.stream()
                .collect(Collectors.toMap(RatingCategoryEntity::getCategoryName, RatingCategoryEntity::getId));
    }

    // Create Map of Rating Category <category,id>
    public Map<String, Boolean> getFilesNameStatusMap(List<FilesEntity> filesEntityList) {
        return filesEntityList.stream()
                .collect(Collectors.toMap(FilesEntity::getFile, FilesEntity::isStatus));
    }

    // Get Thread-pool count based on cpu cores
    public int createCpuBoundThreadPool() {
        return Runtime.getRuntime().availableProcessors() * Constants.THREAD_MULTIPLIER;
    }

}
