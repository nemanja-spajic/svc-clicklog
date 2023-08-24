package com.advertisement.clicklog.controller;

import com.advertisement.clicklog.converter.ClickLogResponseDtoConverter;
import com.advertisement.clicklog.dto.ClickLogResponseDto;
import com.advertisement.clicklog.service.ClickLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.advertisement.clicklog.common.LogCodes.FETCH_CLICKS_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping("api")
public class ClickLogController {

  private static final Logger logger = LoggerFactory.getLogger(ClickLogController.class);

  private final ClickLogResponseDtoConverter clickLogResponseDtoConverter;
  private final ClickLogService clickLogService;
  private final DateTimeFormatter dateTimeFormatter;

  public ClickLogController(
      ClickLogResponseDtoConverter clickLogResponseDtoConverter,
      ClickLogService clickLogService,
      DateTimeFormatter dateTimeFormatter) {
    this.clickLogResponseDtoConverter = clickLogResponseDtoConverter;
    this.clickLogService = clickLogService;
    this.dateTimeFormatter = dateTimeFormatter;
  }

  @GetMapping(
      value = "/campaign/{campaign_id}/clicks",
      produces = {"application/json"})
  @ResponseStatus(value = OK)
  public ResponseEntity<ClickLogResponseDto> fetchClicks(
      @PathVariable("campaign_id") final int campaignId,
      @RequestParam(value = "startDate", required = false) String startDateStr,
      @RequestParam(value = "endDate", required = false) String endDateStr) {
    logger.info(
        "{}, campaignId={}, startDate={}, endDate={}",
        FETCH_CLICKS_REQUEST,
        campaignId,
        startDateStr,
        endDateStr);

    final var clicksNumber =
        clickLogService.getClicks(campaignId, parseDate(startDateStr), parseDate(endDateStr));

    return ResponseEntity.ok(clickLogResponseDtoConverter.convert(clicksNumber));
  }

  private LocalDateTime parseDate(String dateStr) {
    return dateStr != null ? LocalDateTime.parse(dateStr, dateTimeFormatter) : null;
  }
}
