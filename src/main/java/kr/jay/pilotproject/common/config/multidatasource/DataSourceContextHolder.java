package kr.jay.pilotproject.common.config.multidatasource;

import org.springframework.stereotype.Component;

@Component
public class DataSourceContextHolder {

    public static final ThreadLocal<EdcDataSourceConfig> contextHolder = new ThreadLocal<>();

    public static EdcDataSourceConfig getDatabaseCluster() {
        return contextHolder.get();
    }

    public static void setEdcDataSource(EdcDataSourceConfig edcDataSourceConfig) {
        contextHolder.set(edcDataSourceConfig);
    }

    public static void clear() {
        contextHolder.remove();
    }
}