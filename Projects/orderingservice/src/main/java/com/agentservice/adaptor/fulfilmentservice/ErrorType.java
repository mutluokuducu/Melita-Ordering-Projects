package com.agentservice.adaptor.fulfilmentservice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorType {

  WRONG_CUSTOMER_CONSENT_ACTION_FULFILMENT("8027",
      "Requested Action is not applicable to the current state of the fulfilment process",
      "ACTION", BAD_REQUEST);

  private String code;
  private String description;
  private String field;
  private HttpStatus httpStatus;

}
