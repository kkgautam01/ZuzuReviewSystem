package org.zuzureviewsystem.filehandling.adapter;

import com.fasterxml.jackson.databind.JsonNode;
import org.zuzureviewsystem.entity.ProviderEntity;
import org.zuzureviewsystem.filehandling.cloud.aws.AwsConfig;
import org.zuzureviewsystem.filehandling.cloud.Cloud;
import org.zuzureviewsystem.filehandling.cloud.CloudConfig;
import org.zuzureviewsystem.filehandling.service.Provider;

public class AwsConfigAdapter implements CloudConfig {

    @Override
    public Cloud getCloudConfig(JsonNode jsonNode, Provider provider) {
        AwsConfig awsConfig = new AwsConfig(jsonNode);
        Cloud cloud = new Cloud();
        cloud.setSecretKey(awsConfig.getSecretKey());
        cloud.setBucket(awsConfig.getBucket());
        cloud.setAccessKey(awsConfig.getAccessKey());
        cloud.setPrefix(awsConfig.getPrefix());
        cloud.setType(awsConfig.getType());
        cloud.setRegion(awsConfig.getRegion());
        cloud.setPageSize(awsConfig.getPageSize());
        cloud.setProviderName(provider.getName());
        return cloud;
    }
}
