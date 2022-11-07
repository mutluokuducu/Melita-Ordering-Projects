package com.agentservice.services;

import com.agentservice.adaptor.fulfilmentservice.FulfilmentClientImpl;
import com.agentservice.adaptor.request.ApprovedOrder;
import com.agentservice.dto.request.OrderRequest;
import com.agentservice.producer.ProducerMQ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  private ProducerMQ producer;

  @Autowired
  private FulfilmentClientImpl fulfilmentClient;

  private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

  public void sendMessage(OrderRequest orderRequest) {
    producer.sendMessage(orderRequest);
    LOGGER.info("Send msg service :{}",  orderRequest);

  }

  @Override
  public void approvedOrder(ApprovedOrder approvedOrder) {
    fulfilmentClient.approvedOrder(approvedOrder);
  }
}
