package org.zuzureviewsystem.filehandling.service;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.zuzureviewsystem.entity.ProviderEntity;

import java.io.Serializable;

@Getter
@Setter
public class Provider implements Serializable {
    private Long providerId;
    private String name;
    private String cloudInfo;
    public Provider(ProviderEntity entity) {
        this.cloudInfo = entity.getCloudInfo();
        this.name = entity.getName();
        this.providerId = entity.getProviderId();
    }
}
