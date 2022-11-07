package com.agentservice.adaptor.fulfilmentservice;

import com.agentservice.adaptor.request.ApprovedOrder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class FulfilmentClientImpl {

  private final FulfilmentClient fulfilmentClient;
  private static final Logger LOGGER = LoggerFactory.getLogger(FulfilmentClientImpl.class);

  public void approvedOrder(ApprovedOrder approvedOrder) {
    fulfilmentClient.approvedOrder(approvedOrder);

    LOGGER.info("Approved status call the fullfilment service :{}",  approvedOrder);


  }

}

