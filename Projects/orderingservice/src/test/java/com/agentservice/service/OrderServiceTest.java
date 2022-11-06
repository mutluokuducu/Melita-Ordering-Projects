package com.agentservice.service;

import static org.mockito.Mockito.verify;

import com.agentservice.adaptor.fulfilmentservice.FulfilmentClientImpl;
import com.agentservice.utils.ObjectFactory;
import com.agentservice.producer.ProducerMQ;
import com.agentservice.services.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

  @InjectMocks
  private OrderServiceImpl orderService;

  @Mock
  private ProducerMQ producer;

  @Mock
  private FulfilmentClientImpl fulfilmentClient;

  @Test
  void testForConsumerServiceApprovedStatusUpdate() {

    orderService.approvedOrder(ObjectFactory.buildApprovedOrder());

    verify(fulfilmentClient).approvedOrder(ObjectFactory.buildApprovedOrder());
  }

  @Test
  void testForConsumerServicePost() {

    orderService.sendMessage(ObjectFactory.buildOrderRequest());

    verify(producer).sendMessage(ObjectFactory.buildOrderRequest());
  }


}
