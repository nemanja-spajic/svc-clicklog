package com.advertisement.clicklog.controller;

import com.advertisement.clicklog.converter.ClickLogResponseDtoConverter;
import com.advertisement.clicklog.service.ClickLogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClickLogControllerTest {

  @Mock private ClickLogService clickLogService;
  private static final DateTimeFormatter FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  private ClickLogController sut;

  @BeforeEach
  void setUp() {
    sut = new ClickLogController(new ClickLogResponseDtoConverter(), clickLogService, FORMATTER);
  }

  @Test
  @DisplayName(
      "given campaignId equal to 1"
          + "  and startAt equal to null"
          + "  and endAt equal to null"
          + "  and service returns 12 clicks"
          + " when fetchClicks"
          + " then return 200"
          + "  and campaignId should be 1"
          + "  number of clicks should be 12")
  void fetchClicks_givenCampaign_then200() {
    when(clickLogService.getClicks(1, null, null)).thenReturn(12);

    final var response = sut.fetchClicks(1, null, null);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().getNumberOfClicks()).isEqualTo(12);
    assertThat(response.getBody().getCampaignId()).isEqualTo(1);
  }

  @Test
  @DisplayName(
      "given campaignId equal to 1"
          + "  and startAt equal to 2021-01-01 00:00:00"
          + "  and endAt equal to 2021-01-01 00:00:00"
          + "  and service returns 12 clicks"
          + " when fetchClicks"
          + " then return 200"
          + "  and campaignId should be 1"
          + "  and number of clicks should be 12")
  void fetchClicks_givenCampaignWithStartAtAndEndAt_then200() {
    when(clickLogService.getClicks(eq(1), any(), any())).thenReturn(12);

    final var response = sut.fetchClicks(1, "2021-01-01 00:00:00", "2021-01-01 00:00:00");

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().getNumberOfClicks()).isEqualTo(12);
    assertThat(response.getBody().getCampaignId()).isEqualTo(1);
  }

  @ParameterizedTest
  @MethodSource("invalidCampaignTime")
  @DisplayName(
      "given campaignId equal to 1"
          + "  and startAt or endAt is not in right format"
          + " when fetchClicks"
          + " then throw DateTimeParseException")
  void fetchClicks_givenCampaignWithInvalidStarAt_thenException(
      final String startAt, final String endAt, final String expectedErrorMessage) {
    final var exception =
        assertThrows(DateTimeParseException.class, () -> sut.fetchClicks(1, startAt, endAt));

    assertThat(exception.getMessage()).endsWith(expectedErrorMessage);
  }

  private static Stream<Arguments> invalidCampaignTime() {
    return Stream.of(
        Arguments.of(
            "InvalidStartAt",
            "2021-01-01 00:00:00",
            "Text 'InvalidStartAt' could not be parsed at index 0"),
        Arguments.of(
            "2021-01-01 00:00:00",
            "InvalidEndAt",
            "Text 'InvalidEndAt' could not be parsed at index 0"),
        Arguments.of(
            "InvalidStartAt",
            "InvalidEndAt",
            "Text 'InvalidStartAt' could not be parsed at index 0"));
  }
}
