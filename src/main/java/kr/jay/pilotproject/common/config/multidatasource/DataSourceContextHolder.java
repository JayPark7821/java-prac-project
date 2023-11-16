package kr.jay.pilotproject.common.config.multidatasource;

import org.springframework.stereotype.Component;

@Component
public class DataSourceContextHolder {

    public static final ThreadLocal<EdcDataSource> dataSourceHolder = new ThreadLocal<>();

    public static EdcDataSource getDataSource() {
        return dataSourceHolder.get();
    }

    public static void setDataSource(EdcDataSource edcDataSourceConfig) {
        dataSourceHolder.set(edcDataSourceConfig);
    }

    public static void clear() {
        dataSourceHolder.remove();
    }
}