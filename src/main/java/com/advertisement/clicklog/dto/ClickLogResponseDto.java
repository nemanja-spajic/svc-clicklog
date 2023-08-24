package com.advertisement.clicklog.dto;

public class ClickLogResponseDto {
  private int numberOfClicks;

  public ClickLogResponseDto() {}

  private ClickLogResponseDto(Builder builder) {
    numberOfClicks = builder.numberOfClicks;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public int getNumberOfClicks() {
    return numberOfClicks;
  }

  public static final class Builder {
    private int numberOfClicks;

    private Builder() {}

    public Builder withNumberOfClicks(int val) {
      numberOfClicks = val;
      return this;
    }

    public ClickLogResponseDto build() {
      return new ClickLogResponseDto(this);
    }
  }
}
