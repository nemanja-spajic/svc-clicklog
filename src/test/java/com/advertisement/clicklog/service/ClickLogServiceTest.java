package com.advertisement.clicklog.service;

import com.advertisement.clicklog.repository.ClickLogRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ClickLogServiceTest {

  @Mock private ClickLogRepository clickLogRepository;
  @InjectMocks private ClickLogService sut;

  @Test
  @DisplayName(
      "given campaignId equal to 1"
          + "  and startAt equal to null"
          + "  and endAt equal to null"
          + " when getClicks"
          + " then clickLogRepository.countAllByCampaign should be called")
  void getClicks_nullTimes() {
    sut.getClicks(1, null, null);

    verify(clickLogRepository).countAllByCampaign(1);
  }

  @Test
  @DisplayName(
      "given campaignId equal to 1"
          + "  and startAt not equal to null"
          + "  and endAt equal to null"
          + " when getClicks"
          + " then clickLogRepository.countByCampaignAndTimestampAfter should be called")
  void getClicks_startAt() {
    final var startAt = LocalDateTime.now();
    sut.getClicks(1, startAt, null);

    verify(clickLogRepository).countByCampaignAndTimestampAfter(1, startAt);
  }

  @Test
  @DisplayName(
      "given campaignId equal to 1"
          + "  and startAt equal to null"
          + "  and endAt not equal to null"
          + " when getClicks"
          + " then clickLogRepository.countByCampaignAndTimestampBefore should be called")
  void getClicks_endAt() {
    final var endAt = LocalDateTime.now();
    sut.getClicks(1, null, endAt);

    verify(clickLogRepository).countByCampaignAndTimestampBefore(1, endAt);
  }

  @Test
  @DisplayName(
      "given campaignId equal to 1"
          + "  and startAt not equal to null"
          + "  and endAt not equal to null"
          + " when getClicks"
          + " then clickLogRepository.countByCampaignAndTimestampBetween should be called")
  void getClicks_bothTimes() {
    final var startAt = LocalDateTime.now();
    final var endAt = LocalDateTime.now();
    sut.getClicks(1, startAt, endAt);

    verify(clickLogRepository).countByCampaignAndTimestampBetween(1, startAt, endAt);
  }
}
