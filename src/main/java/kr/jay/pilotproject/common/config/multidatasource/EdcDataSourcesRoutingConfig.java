package kr.jay.pilotproject.common.config.multidatasource;

import com.atomikos.spring.AtomikosDataSourceBean;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.managed.ManagedTransactionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.mybatis.spring.SqlSessionFactoryBean;
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


@Slf4j
@DependsOn("multiTxManager")
@Configuration
@EnableJpaRepositories(
    basePackages = "kr.jay.pilotproject",
    transactionManagerRef = "multiTxManager",
    entityManagerFactoryRef = "xaEntityManagerFactory"
)
@EnableTransactionManagement
@RequiredArgsConstructor
public class EdcDataSourcesRoutingConfig {

    private final EdcDataSourcesConfig edcDataSourcesConfig;
    private final JpaVendorAdapter jpaVendorAdapter;

    @Primary
    @Bean
    public DataSource createEdcDataSource() {
        Map<Object, Object> dataSources = new HashMap<>();

        edcDataSourcesConfig.getDatasource()
            .forEach((key, value) ->
                dataSources.put(
                    EdcDataSource.valueOf(key),
                    createAtomikosDataSource(key, value)
                )
            );

        EdcDataSourceRouter routingDataSource = new EdcDataSourceRouter();
        routingDataSource.setTargetDataSources(dataSources);
        routingDataSource.setDefaultTargetDataSource(dataSources.get(EdcDataSource.BUILDER));

        return routingDataSource;
    }

    private AtomikosDataSourceBean createAtomikosDataSource(String key, DataSourceProperties value) {
        Properties xaProperties = new Properties();
        xaProperties.put("user", value.getUsername());
        xaProperties.put("password", value.getPassword());
        xaProperties.put("url", value.getUrl());

        AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
        dataSource.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
        dataSource.setUniqueResourceName(key);
        dataSource.setXaProperties(xaProperties);

        return dataSource;
    }

    @Bean(name = "xaEntityManagerFactory")
    @DependsOn("multiTxManager")
    public LocalContainerEntityManagerFactoryBean xaEntityManagerFactory() {
        log.info("==================== legacyEntityManagerFactory");
        Properties properties = new Properties();
        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
        properties.put("javax.persistence.transactionType", "JTA");
        properties.put("hibernate.physical_naming_strategy",
            CamelCaseToUnderscoresNamingStrategy.class.getName());
        properties.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());

        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(createEdcDataSource());
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);
        entityManager.setPackagesToScan("kr.jay.pilotproject");
        entityManager.setJpaProperties(properties);

        return entityManager;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);

        factory.setTransactionFactory(new ManagedTransactionFactory());
        return factory.getObject();
    }
}