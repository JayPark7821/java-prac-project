package kr.jay.pilotprojcet.common.config;

import java.util.Map;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.jay.pilotprojcet.common.utils.HibernateQueryCounter;
import lombok.RequiredArgsConstructor;

/**
 * HibernateConfig
 *
 * @author jaypark
 * @version 1.0.0
 * @since 10/28/23
 */

@Configuration
@RequiredArgsConstructor
public class HibernateConfig {

	private final HibernateQueryCounter hibernateQueryCounter;
	@Bean
	public HibernatePropertiesCustomizer configureStatementInspector(){
		return hibernateProperties -> hibernateProperties.put(
			AvailableSettings.STATEMENT_INSPECTOR,
			"kr.jay.pilotproject.common.utils.HibernateQueryCounter"
		);
	}
}
