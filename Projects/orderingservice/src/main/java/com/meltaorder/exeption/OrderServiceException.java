package com.meltaorder.exeption;

import lombok.Getter;

@Getter
public class OrderServiceException extends RuntimeException {

  private final ErrorType errorType;

  public OrderServiceException(ErrorType errorType) {
    this.errorType = errorType;
  }
}
