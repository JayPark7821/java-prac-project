package kr.jay.tobyspring;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import kr.jay.tobyspring.data.OrderRepository;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * DataConfig
 *
 * @author jaypark
 * @version 1.0.0
 * @since 8/10/24
 */
@Configuration
public class DataConfig {
    //data source
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .build();
    }

    // entity manager factory
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("kr.jay.tobyspring");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter(){
            {
                setDatabase(Database.H2);
                setGenerateDdl(true);
                setShowSql(true);
            }
        });
        return em;
    }

    @Bean
    public BeanPostProcessor persistenceAnnotationBeanPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public OrderRepository orderRepository() {
        return new OrderRepository();
    }
}
