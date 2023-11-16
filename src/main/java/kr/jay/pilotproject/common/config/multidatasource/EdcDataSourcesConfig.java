package kr.jay.pilotproject.common.config.multidatasource;

import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "edc")
public class EdcDataSourcesConfig {
	private Map<String, DataSourceProperties> datasource;
}