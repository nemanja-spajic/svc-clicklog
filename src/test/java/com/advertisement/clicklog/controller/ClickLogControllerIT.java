package com.advertisement.clicklog.controller;

import com.advertisement.clicklog.dto.ClickLogResponseDto;
import com.advertisement.clicklog.exception.ErrorResponse;
import com.advertisement.clicklog.repository.ClickLogRepository;
import io.restassured.RestAssured;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.advertisement.clicklog.fixtures.ClickLogFixture.createClickLogBuilder;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClickLogControllerIT {
  private static final DateTimeFormatter FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  private static final int CAMPAIGN_ID = 2;
  @Autowired ClickLogRepository clickLogRepository;
  @LocalServerPort protected int port;

  @BeforeEach
  protected void beforeEach() {
    RestAssured.port = port;
  }

  @AfterEach
  protected void afterEach() {
    clickLogRepository.deleteAll();
    clickLogRepository.flush();
  }

  @Test
  @DisplayName(
      "given a campaign ID"
          + "  and startAt and endAt equal to null"
          + "  and 2 records inside the database for the campaign ID"
          + " when fetchClicks"
          + " then return 200"
          + "  and expected number of clicks is 2")
  void fetchClicks_twoCampaigns_then200() {
    saveClickLog(CAMPAIGN_ID);
    saveClickLog(CAMPAIGN_ID);

    final var result =
        given()
            .port(port)
            .get("/api/campaign/" + CAMPAIGN_ID + "/clicks")
            .then()
            .statusCode(200)
            .extract()
            .as(ClickLogResponseDto.class);

    assertThat(result.getNumberOfClicks()).isEqualTo(2);
  }

  @Test
  @DisplayName(
      "given a campaign ID"
          + "  and startAt and endAt are not null"
          + "  and 3 records inside the database for the campaign ID"
          + "  and 2 records are in between the startAt and endAt"
          + " when fetchClicks"
          + " then return 200"
          + "  and expected number of clicks is 2")
  void fetchClicks_twoCampaignsInBetween_then200() {
    final var now = LocalDateTime.now();
    final var tomorrow = now.minusDays(1);
    saveClickLog(CAMPAIGN_ID, now);
    saveClickLog(CAMPAIGN_ID, now);
    saveClickLog(CAMPAIGN_ID, tomorrow);

    final var result =
        given()
            .port(port)
            .queryParam("startDate", now.minusHours(1).format(FORMATTER))
            .queryParam("endDate", now.plusHours(1).format(FORMATTER))
            .get("/api/campaign/" + CAMPAIGN_ID + "/clicks")
            .then()
            .statusCode(200)
            .extract()
            .as(ClickLogResponseDto.class);

    assertThat(result.getNumberOfClicks()).isEqualTo(2);
  }

  @Test
  @DisplayName(
      "given a campaign ID"
          + "  and startAt is in wrong format"
          + " when fetchClicks"
          + " then return 400"
          + "  and status of error is 400"
          + "  and error is Bad Request"
          + "  and message is Invalid date format. Please use yyyy-MM-dd HH:mm:ss format.")
  void fetchClicks_givenWrongDateFormat_then400() {
    final var result =
        given()
            .port(port)
            .queryParam("startDate", "WRONG_DATE_FORMAT")
            .get("/api/campaign/" + CAMPAIGN_ID + "/clicks")
            .then()
            .statusCode(400)
            .extract()
            .as(ErrorResponse.class);

    assertThat(result.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    assertThat(result.getError()).isEqualTo(HttpStatus.BAD_REQUEST.getReasonPhrase());
    assertThat(result.getMessage())
        .isEqualTo("Invalid date format. Please use yyyy-MM-dd HH:mm:ss format.");
  }

  private void saveClickLog(int campaignId, LocalDateTime timestamp) {
    clickLogRepository.save(
        createClickLogBuilder().withCampaign(campaignId).withTimestamp(timestamp).build());
  }

  private void saveClickLog(int campaignId) {
    clickLogRepository.save(createClickLogBuilder().withCampaign(campaignId).build());
  }
}
