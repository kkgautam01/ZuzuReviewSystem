package org.zuzureviewsystem.fileprocessing.strategy;

import org.zuzureviewsystem.fileprocessing.adapter.AgodaAdapter;
import org.zuzureviewsystem.fileprocessing.adapter.ProviderAdapter;


public class AgodaAdapterStrategy implements ProviderAdapterStrategy {
    @Override
    public ProviderAdapter getAdapter() {
        return new AgodaAdapter();  // Return Agoda-specific adapter
    }
}
