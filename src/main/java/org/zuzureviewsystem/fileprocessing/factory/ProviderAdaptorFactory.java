package org.zuzureviewsystem.fileprocessing.factory;

import org.springframework.stereotype.Component;
import org.zuzureviewsystem.fileprocessing.strategy.AgodaAdapterStrategy;
import org.zuzureviewsystem.fileprocessing.strategy.ProviderAdapterStrategy;
import org.zuzureviewsystem.util.Constants;

@Component
public class ProviderAdaptorFactory {
    public ProviderAdaptorFactory() {
    }

    public ProviderAdapterStrategy getStrategy(String providerName) {
        switch (providerName.toLowerCase()) {
            case Constants.AGODA -> {
                return new AgodaAdapterStrategy();
            }
            default -> throw new IllegalArgumentException("Unsupported provider: " + providerName);
        }
    }
}
