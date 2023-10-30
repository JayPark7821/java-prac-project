package kr.jay.pilotproject.common.config;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * HibernateConfig
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */

@Configuration
public class HibernateConfig {

	@Bean
	public HibernatePropertiesCustomizer configureStatementInspector(){
		return hibernateProperties -> hibernateProperties.put(
			AvailableSettings.STATEMENT_INSPECTOR,
			"kr.jay.pilotproject.common.utils.HibernateQueryCounter"
		);
	}
}
