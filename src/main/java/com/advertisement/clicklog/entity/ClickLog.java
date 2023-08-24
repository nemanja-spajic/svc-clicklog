package com.advertisement.clicklog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "advertisement_logs")
public class ClickLog {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private int id;

  @NotNull private LocalDateTime timestamp;
  @NotNull private String type;
  @NotNull private int campaign;
  @NotNull private int banner;
  @NotNull private int contentUnit;
  @NotNull private int network;
  @NotNull private int browser;
  @NotNull private int operatingSystem;
  private Integer country;
  private Integer state;
  private Integer city;

  private ClickLog(Builder builder) {
    id = builder.id;
    timestamp = builder.timestamp;
    type = builder.type;
    campaign = builder.campaign;
    banner = builder.banner;
    contentUnit = builder.contentUnit;
    network = builder.network;
    browser = builder.browser;
    operatingSystem = builder.operatingSystem;
    country = builder.country;
    state = builder.state;
    city = builder.city;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public ClickLog() {}

  public int getId() {
    return id;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public String getType() {
    return type;
  }

  public int getCampaign() {
    return campaign;
  }

  public int getBanner() {
    return banner;
  }

  public int getContentUnit() {
    return contentUnit;
  }

  public int getNetwork() {
    return network;
  }

  public int getBrowser() {
    return browser;
  }

  public int getOperatingSystem() {
    return operatingSystem;
  }

  public int getCountry() {
    return country;
  }

  public int getState() {
    return state;
  }

  public int getCity() {
    return city;
  }

  public static final class Builder {
    private int id;
    private LocalDateTime timestamp;
    private String type;
    private int campaign;
    private int banner;
    private int contentUnit;
    private int network;
    private int browser;
    private int operatingSystem;
    private int country;
    private int state;
    private int city;

    private Builder() {}

    public Builder withId(int val) {
      id = val;
      return this;
    }

    public Builder withTimestamp(LocalDateTime val) {
      timestamp = val;
      return this;
    }

    public Builder withType(String val) {
      type = val;
      return this;
    }

    public Builder withCampaign(int val) {
      campaign = val;
      return this;
    }

    public Builder withBanner(int val) {
      banner = val;
      return this;
    }

    public Builder withContentUnit(int val) {
      contentUnit = val;
      return this;
    }

    public Builder withNetwork(int val) {
      network = val;
      return this;
    }

    public Builder withBrowser(int val) {
      browser = val;
      return this;
    }

    public Builder withOperatingSystem(int val) {
      operatingSystem = val;
      return this;
    }

    public Builder withCountry(int val) {
      country = val;
      return this;
    }

    public Builder withState(int val) {
      state = val;
      return this;
    }

    public Builder withCity(int val) {
      city = val;
      return this;
    }

    public ClickLog build() {
      return new ClickLog(this);
    }
  }
}
