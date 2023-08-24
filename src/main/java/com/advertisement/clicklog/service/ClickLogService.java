package com.advertisement.clicklog.service;

import com.advertisement.clicklog.repository.ClickLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClickLogService {
  private final ClickLogRepository clickLogRepository;

  public ClickLogService(ClickLogRepository clickLogRepository) {
    this.clickLogRepository = clickLogRepository;
  }

  /**
   * Retrieves the number of clicks for a specific campaign within the given time range.
   *
   * @param campaignId The ID of the campaign for which to retrieve the click count.
   * @param startAt The starting date and time of the time range. If null, no lower bound is
   *     applied.
   * @param endAt The ending date and time of the time range. If null, no upper bound is applied.
   * @return The number of clicks for the specified campaign within the provided time range.
   */
  public int getClicks(
      final int campaignId, final LocalDateTime startAt, final LocalDateTime endAt) {
    if (startAt == null && endAt == null) {
      return clickLogRepository.countAllByCampaign(campaignId);
    }
    if (startAt != null && endAt == null) {
      return clickLogRepository.countByCampaignAndTimestampAfter(campaignId, startAt);
    }
    if (startAt == null && endAt != null) {
      return clickLogRepository.countByCampaignAndTimestampBefore(campaignId, endAt);
    }
    return clickLogRepository.countByCampaignAndTimestampBetween(campaignId, startAt, endAt);
  }
}
