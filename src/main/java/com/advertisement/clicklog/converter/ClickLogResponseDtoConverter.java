package com.advertisement.clicklog.converter;

import com.advertisement.clicklog.dto.ClickLogResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ClickLogResponseDtoConverter {

  public ClickLogResponseDto convert(final int clicksNumber, final int campaignId) {
    return ClickLogResponseDto.newBuilder()
        .withNumberOfClicks(clicksNumber)
        .withCampaignId(campaignId)
        .build();
  }
}
