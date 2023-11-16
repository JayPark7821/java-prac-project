package kr.jay.pilotproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;
import kr.jay.pilotproject.common.config.multidatasource.EdcDataSourcesConfig;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class PilotProjectApplication {

	private final EdcDataSourcesConfig edcDataSourcesConfig;

	public static void main(String[] args) {
		SpringApplication.run(PilotProjectApplication.class, args);
	}

	@PostConstruct
	void init() {
		edcDataSourcesConfig.getDatasource().forEach((key, value) -> {
			System.out.println("=== " + key);
			System.out.println("=== " + value.getUrl());
		});

	}
}
