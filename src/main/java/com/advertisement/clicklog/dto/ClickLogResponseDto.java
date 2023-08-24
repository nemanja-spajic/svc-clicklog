package com.advertisement.clicklog.dto;

public class ClickLogResponseDto {

  private int numberOfClicks;
  private int campaignId;

  public ClickLogResponseDto() {}

  private ClickLogResponseDto(Builder builder) {
    numberOfClicks = builder.numberOfClicks;
    campaignId = builder.campaignId;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public int getNumberOfClicks() {
    return numberOfClicks;
  }

  public int getCampaignId() {
    return campaignId;
  }

  public static final class Builder {
    private int numberOfClicks;
    private int campaignId;

    private Builder() {}

    public Builder withNumberOfClicks(int val) {
      numberOfClicks = val;
      return this;
    }

    public Builder withCampaignId(int val) {
      campaignId = val;
      return this;
    }

    public ClickLogResponseDto build() {
      return new ClickLogResponseDto(this);
    }
  }
}
