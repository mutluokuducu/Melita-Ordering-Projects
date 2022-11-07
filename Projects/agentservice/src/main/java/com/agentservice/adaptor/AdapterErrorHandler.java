package com.agentservice.adaptor;

import com.agentservice.adaptor.fulfilmentservice.ErrorType;
import com.agentservice.exeption.AdapterException;
import com.agentservice.exeption.NotApplicableActionException;
import com.agentservice.exeption.OrderServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component

public class AdapterErrorHandler implements ErrorDecoder {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public Exception decode(String methodKey, Response response) {
    ErrorMessage errorMessage = getErrorMessage(response);
    String code = errorMessage.getErrors().get(0).getCode();

    if (code.equals(ErrorType.WRONG_CUSTOMER_CONSENT_ACTION_FULFILMENT.getCode())) {
      log.error("Error Code: {}, Status Code: {},  Method: {}", code, response.status(), methodKey);
      throw new NotApplicableActionException(
          com.agentservice.exeption.ErrorType.WRONG_CUSTOMER_CONSENT_ACTION);
    }

    log.error("Response: {}, Status Code: {}, Method: {}", response, response.status(), methodKey);
    throw new AdapterException(errorMessage);
  }

  private String getResponseBody(Response response) {
    try {
      return new String(Util.toByteArray(response.body().asInputStream()));
    } catch (Exception e) {
      log.error("Failed to process response: {}", response);
      throw new OrderServiceException(com.agentservice.exeption.ErrorType.UNKNOWN_RESPONSE_RECEIVED);
    }
  }

  private ErrorMessage getErrorMessage(
      Response response) {
    String responseBody = getResponseBody(response);

    try {
      ErrorMessage errorMessage = objectMapper.readValue(
          responseBody, ErrorMessage.class);
      errorMessage.setHttpStatus(HttpStatus.valueOf(response.status()));
      return errorMessage;
    } catch (IOException e) {
      log.error("Failed to process response: {}", response);
      throw new OrderServiceException(com.agentservice.exeption.ErrorType.UNKNOWN_RESPONSE_RECEIVED);
    }
  }

}