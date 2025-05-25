package org.zuzureviewsystem.filehandling.factory;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.zuzureviewsystem.entity.ProviderEntity;
import org.zuzureviewsystem.filehandling.adapter.AwsConfigAdapter;
import org.zuzureviewsystem.filehandling.cloud.Cloud;
import org.zuzureviewsystem.filehandling.service.Provider;
import org.zuzureviewsystem.util.Constants;
import org.zuzureviewsystem.util.LogMessages;

@Service
public class CloudConfigAdapterFactory {

    public Cloud getConfig(String cloudType, JsonNode jsonNode, Provider provider){
        // extend class with gcp, azure, etc
        switch(cloudType){
            case Constants.AWS -> {
                AwsConfigAdapter awsConfigAdapter = new AwsConfigAdapter();
                return awsConfigAdapter.getCloudConfig(jsonNode, provider);
            }default -> {
                throw new IllegalArgumentException(LogMessages.UNSUPPORTED_CLOUD_CONFIG);
            }
        }
    }
}
