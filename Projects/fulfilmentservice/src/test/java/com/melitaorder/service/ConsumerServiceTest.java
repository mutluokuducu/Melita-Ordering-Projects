package com.melitaorder.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.melitaorder.utils.ObjectFactory;
import com.meltaorder.repository.OrderingRepository;
import com.meltaorder.repository.entity.PersonalDetails;
import com.meltaorder.services.ConsumerService;
import com.meltaorder.services.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConsumerServiceTest {

  @Mock
  private OrderingRepository orderingRepository;

  @Mock
  private EmailService emailService;

  @InjectMocks
  private ConsumerService consumerService;

  @Test
  void testForConsumerService() {
    PersonalDetails personalDetails = ObjectFactory.buildPersonalDetails();
    personalDetails.setId(0L);
    consumerService.saveOrder(ObjectFactory.buildOrderResponse());
    verify(orderingRepository).save(ObjectFactory.buildPersonalDetails());
    verify(orderingRepository, times(1)).save(personalDetails);
    verify(emailService).sendEmail(personalDetails);
    verify(emailService, times(1)).sendEmail(personalDetails);

  }

}
