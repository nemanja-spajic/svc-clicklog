package com.advertisement.clicklog.fixtures;

import com.advertisement.clicklog.entity.ClickLog;

import java.time.LocalDateTime;

public class ClickLogFixture {
  private static final LocalDateTime TIMESTAMP = LocalDateTime.now();
  private static final String TYPE = "TYPE";
  private static final int CAMPAIGN = 1;
  private static final int BANNER = 2;
  private static final int CONTENT_UNIT = 3;
  private static final int NETWORK = 4;
  private static final int BROWSER = 5;
  private static final int OPERATING_SYSTEM = 6;
  private static final int COUNTRY = 7;
  private static final int STATE = 8;
  private static final int CITY = 9;

  public static ClickLog.Builder createClickLogBuilder() {
    return ClickLog.newBuilder()
        .withTimestamp(TIMESTAMP)
        .withType(TYPE)
        .withCampaign(CAMPAIGN)
        .withBanner(BANNER)
        .withContentUnit(CONTENT_UNIT)
        .withNetwork(NETWORK)
        .withBrowser(BROWSER)
        .withOperatingSystem(OPERATING_SYSTEM)
        .withCountry(COUNTRY)
        .withState(STATE)
        .withCity(CITY);
  }

  public static ClickLog createClickLog() {
    return createClickLogBuilder().build();
  }
}
