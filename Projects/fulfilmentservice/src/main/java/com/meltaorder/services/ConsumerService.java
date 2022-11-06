package com.meltaorder.services;

import static com.meltaorder.mapper.OrderMapper.getPersonalDetails;

import com.meltaorder.dto.response.OrderResponse;
import com.meltaorder.repository.OrderingRepository;
import com.meltaorder.repository.entity.PersonalDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

  @Autowired
  private OrderingRepository orderingRepository;

  @Autowired
  private EmailService emailService;

  private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerService.class);

  @RabbitListener(queues = "${spring.rabbitmq.queue}")
  public void saveOrder(OrderResponse orderResponse) {

    PersonalDetails personalDetails = getPersonalDetails(orderResponse);

    orderingRepository.save(personalDetails);
    emailService.sendEmail(personalDetails);

    LOGGER.info("Message Received queue-> {}", orderResponse);

  }

}
