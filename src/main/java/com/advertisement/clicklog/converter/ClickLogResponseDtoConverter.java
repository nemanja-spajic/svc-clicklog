package com.advertisement.clicklog.converter;

import com.advertisement.clicklog.dto.ClickLogResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ClickLogResponseDtoConverter {

  public ClickLogResponseDto convert(final int clicksNumber) {
    return ClickLogResponseDto.newBuilder().withNumberOfClicks(clicksNumber).build();
  }
}
