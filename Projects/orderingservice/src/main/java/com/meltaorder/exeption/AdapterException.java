package com.meltaorder.exeption;

import com.meltaorder.adaptor.ErrorMessage;
import lombok.Getter;

@Getter
public class AdapterException extends RuntimeException {

  private final ErrorMessage errorMessage;

  public AdapterException(ErrorMessage errorMessage) {
    super(errorMessage.getErrors().get(0).getDescription());
    this.errorMessage = errorMessage;
  }
}
