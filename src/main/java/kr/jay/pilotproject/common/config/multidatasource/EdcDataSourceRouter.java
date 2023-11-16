package kr.jay.pilotproject.common.config.multidatasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class EdcDataSourceRouter extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }
}