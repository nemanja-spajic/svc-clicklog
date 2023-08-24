package com.advertisement.clicklog.exception;

public class ErrorResponse {

  private int status;
  private String error;
  private String message;

  public ErrorResponse() {}

  private ErrorResponse(Builder builder) {
    status = builder.status;
    error = builder.error;
    message = builder.message;
  }

  public int getStatus() {
    return status;
  }

  public String getError() {
    return error;
  }

  public String getMessage() {
    return message;
  }

  public static final class Builder {
    private int status;
    private String error;
    private String message;

    private Builder() {}

    public static Builder newBuilder() {
      return new Builder();
    }

    public Builder withStatus(int val) {
      status = val;
      return this;
    }

    public Builder withError(String val) {
      error = val;
      return this;
    }

    public Builder withMessage(String val) {
      message = val;
      return this;
    }

    public ErrorResponse build() {
      return new ErrorResponse(this);
    }
  }
}
