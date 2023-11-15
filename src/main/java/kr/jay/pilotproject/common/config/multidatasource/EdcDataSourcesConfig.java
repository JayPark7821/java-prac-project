package kr.jay.pilotproject.common.config.multidatasource;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "edc")
public class EdcDataSourcesConfig {
   private List<EdcDataSourceConfig> namedDataSources;
}