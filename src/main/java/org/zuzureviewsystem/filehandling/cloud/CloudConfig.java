package org.zuzureviewsystem.filehandling.cloud;

import com.fasterxml.jackson.databind.JsonNode;
import org.zuzureviewsystem.entity.ProviderEntity;
import org.zuzureviewsystem.filehandling.service.Provider;

public interface CloudConfig {
    public Cloud getCloudConfig(JsonNode jsonNode, Provider provider);
}
