package kr.jay.pilotproject;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import kr.jay.pilotproject.common.jpa.audit.AuditLog;
import kr.jay.pilotproject.infrastructure.persistance.audit.AuditLogJpaRepository;
import kr.jay.pilotproject.interfaces.dto.UserJoinRequest;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("classpath:test-data.sql")
class PilotProjectApplicationTests {

	@LocalServerPort
	private int port;
	private static final String USER_BASE_URL = "/api/v1/user";
	private static final String POST_BASE_URL = "/api/v1/post";

	@Autowired
	private AuditLogJpaRepository auditLogJpaRepository;

	@BeforeEach
	void setUp() {
		RestAssured.port = port;
	}

	@Test
	void auditLogTest() throws Exception {
		final UserJoinRequest request = new UserJoinRequest("testUser");

		RestAssured.

			given().log().all()
			.contentType(ContentType.JSON)
			.body(request).

			when()
			.post(USER_BASE_URL).

			then()
			.statusCode(200);

		final List<AuditLog> auditLogs = auditLogJpaRepository.findAll();
		Assertions.assertThat(auditLogs.size()).isEqualTo(1);
	}

	@Test
	void NPlus1Test() throws Exception {
		RestAssured.

			given().log().all().

			when()
			.get(POST_BASE_URL).

			then()
			.statusCode(200);
	}
}
