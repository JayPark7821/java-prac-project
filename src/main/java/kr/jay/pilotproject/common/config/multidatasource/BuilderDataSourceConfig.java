package kr.jay.pilotproject.common.config.multidatasource;

import com.atomikos.spring.AtomikosDataSourceBean;
import java.util.Properties;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * BuilderDataSourceConfig
 *
 * @author jaypark
 * @version 1.0.0
 * @since 1/12/24
 */


@Slf4j
@Configuration
@RequiredArgsConstructor
@DependsOn("multiTxManager")
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = {"kr.jay.pilotproject.infrastructure.persistance.builder"}
    , entityManagerFactoryRef = "builderEntityManagerFactory"
    , transactionManagerRef = "multiTxManager"
)
@EntityScan("kr.jay.pilotproject.domain.builder")
public class BuilderDataSourceConfig {

    private final EdcDataSourcesConfig edcDataSourcesConfig;
    private final JpaVendorAdapter jpaVendorAdapter;

    @Primary
    @Bean(name = "builderDataSource")
    public DataSource builderDataSource() {
        DataSourceProperties builder = edcDataSourcesConfig.getDatasource().get("BUILDER");

        Properties xaProperties = new Properties();
        xaProperties.put("user", builder.getUsername());
        xaProperties.put("password", builder.getPassword());
        xaProperties.put("url", builder.getUrl());

        AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
        dataSource.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
        dataSource.setUniqueResourceName("BUILDER");
        dataSource.setXaProperties(xaProperties);
        return dataSource;
    }

    @Primary
    @Bean(name = "builderEntityManagerFactory")
    @DependsOn("multiTxManager")
    public LocalContainerEntityManagerFactoryBean builderEntityManagerFactory() {
        log.info("==================== builderEntityManagerFactory");
        Properties properties = new Properties();
        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
        properties.put("javax.persistence.transactionType", "JTA");
        properties.put("hibernate.physical_naming_strategy",
            CamelCaseToUnderscoresNamingStrategy.class.getName());
        properties.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());

        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(builderDataSource());
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);
        entityManager.setPackagesToScan("kr.jay.pilotproject.domain.builder");
        entityManager.setJpaProperties(properties);

        return entityManager;
    }

}
