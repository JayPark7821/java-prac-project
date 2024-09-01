package com.example.userserver;

import com.example.userserver.users.UserInfo;
import com.example.userserver.users.UserRequest;
import com.example.userserver.users.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * UserCreateIntegrateTest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 9/1/24
 */
@SpringBootTest
@Testcontainers
class UserCreateIntegrateTest {

    @Container
    private static final MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
        .withInitScript("init.sql")
        .withDatabaseName("user")
        .withUsername("user")
        .withPassword("password");

    @Autowired
    private UserService userService;

    @DynamicPropertySource
    static void registerMySqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

    @Test
    void createUser(){
        UserRequest userRequest = new UserRequest("test", "test@test.com", "a");
        UserInfo createdUser = userService.createUser(userRequest);

        Assertions.assertThat(createdUser.getUsername()).isEqualTo(userRequest.getUsername());
    }
}
