package com.github.mateuszrasinski.recruitment.router.controller;

import com.github.mateuszrasinski.recruitment.router.dao.Event;
import com.github.mateuszrasinski.recruitment.router.dao.EventStatus;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EventControllerTest {

  @LocalServerPort
  private Integer port;

  static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
      "postgres:latest"
  );

  @BeforeAll
  static void beforeAll() {
    postgres.start();
  }

  @AfterAll
  static void afterAll() {
    postgres.stop();
  }

  @DynamicPropertySource
  static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgres::getJdbcUrl);
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.datasource.password", postgres::getPassword);
  }

  @BeforeEach
  void setUp() {
    RestAssured.baseURI = "http://localhost:" + port;
  }

  @Test
  void getAllEvents() {
    var result = RestAssured.given()
        .contentType(ContentType.JSON)
        .when()
        .get("/event")
        .then()
        .statusCode(200)
        .extract().jsonPath()
        .getList("$", Event.class);
    assertEquals(4, result.size());
    assertEquals(1, result.get(0).getId());
    assertEquals("EVENT_1", result.get(0).getCode());
    assertEquals(2, result.get(1).getId());
    assertEquals("EVENT_2", result.get(1).getCode());
    assertEquals(3, result.get(2).getId());
    assertEquals("EVENT_3", result.get(2).getCode());
    assertEquals(4, result.get(3).getId());
    assertEquals("EVENT_4", result.get(3).getCode());
  }

  @Test
  void getEvent() {

    RestAssured.given()
        .contentType(ContentType.JSON)
        .when()
        .get("/event/1")
        .then()
        .statusCode(200)
        .body("id", Matchers.equalTo(1))
        .body("code", Matchers.equalTo("EVENT_1"))
        .body("status", Matchers.equalTo(EventStatus.PROCESSED.toString()))
        .body("userCode", Matchers.equalTo("USER_123"))
        .body("userUuid", Matchers.equalTo("123e4567-e89b-12d3-a456-426614174000"))
        .body("payload", Matchers.equalTo("Payload for event 1"));
  }

  @Test
  void create() {
    Event event = new Event();
    event.setId(4L);
    event.setCode("TEST_EVENT");
    event.setStatus(EventStatus.IN_PROGRESS);
    event.setDate(LocalDate.now());
    event.setUserCode("USER_123");
    event.setUserUuid("123e4567-e89b-12d3-a456-426614174000");
    event.setPayload("Test payload");

    RestAssured.given()
        .contentType(ContentType.JSON)
        .body(event)
        .when()
        .post("/event")
        .then()
        .statusCode(200);
  }
}