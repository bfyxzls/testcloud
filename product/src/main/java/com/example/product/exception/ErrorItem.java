package com.example.product.exception;

import lombok.ToString;

@ToString
public class ErrorItem {
  private String code;
  private Object value;
  private String message;

  ErrorItem(final String code, final Object value, final String message) {
    this.code = code;
    this.value = value;
    this.message = message;
  }

  public static ErrorItemBuilder builder() {
    return new ErrorItemBuilder();
  }

  public String getCode() {
    return this.code;
  }

  public Object getValue() {
    return this.value;
  }

  public String getMessage() {
    return this.message;
  }

  public static class ErrorItemBuilder {
    private String code;
    private Object value;
    private String message;

    ErrorItemBuilder() {
    }

    public ErrorItemBuilder code(final String code) {
      this.code = code;
      return this;
    }

    public ErrorItemBuilder value(final Object value) {
      this.value = value;
      return this;
    }

    public ErrorItemBuilder message(final String message) {
      this.message = message;
      return this;
    }

    public ErrorItem build() {
      return new ErrorItem(this.code, this.value, this.message);
    }

  }
}