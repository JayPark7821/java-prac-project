package kr.jay.springsecurityprac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ConfigurationPropertiesScan
@SpringBootApplication
public class SpringSecurityPracApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityPracApplication.class, args);
	}

}
