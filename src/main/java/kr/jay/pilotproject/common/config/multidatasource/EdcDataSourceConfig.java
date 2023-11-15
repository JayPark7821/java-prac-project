package kr.jay.pilotproject.common.config.multidatasource;

import com.zaxxer.hikari.HikariConfig;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EdcDataSourceConfig {
   private String name;
   private HikariConfig hikari;
}