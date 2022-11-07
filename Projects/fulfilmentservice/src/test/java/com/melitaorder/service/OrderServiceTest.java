package com.melitaorder.service;

import static com.meltaorder.exeption.ErrorType.ORDER_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.melitaorder.utils.ObjectFactory;
import com.meltaorder.dto.request.ApprovedOrder;
import com.meltaorder.exeption.OrderServiceException;
import com.meltaorder.repository.OrderingRepository;
import com.meltaorder.repository.entity.PersonalDetails;
import com.meltaorder.services.OrderService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

  @InjectMocks
  private OrderService orderService;

  @Mock
  private OrderingRepository orderingRepository;

  @Test
  void testForConsumerServiceApprovedStatusUpdate() {
    Optional<PersonalDetails> personalDetails = Optional.of(ObjectFactory.buildPersonalDetails());
    when(orderingRepository.findById(1L)).thenReturn(personalDetails);
    orderService.approvedOrder(ObjectFactory.buildApprovedOrder());

    verify(orderingRepository).save(ObjectFactory.buildPersonalDetails());
    verify(orderingRepository, times(1)).save(personalDetails.get());

  }

  @Test
  void testForConsumerServiceApprovedStatusNotUpdate_WhenIdIsNotFound() {
    Optional<PersonalDetails> personalDetails = Optional.empty();
    ApprovedOrder approvedOrder = ObjectFactory.buildApprovedOrder();

    when(orderingRepository.findById(1L)).thenReturn(personalDetails);

    OrderServiceException exceptionThrown =
        assertThrows(
            OrderServiceException.class,
            () ->
                orderService.approvedOrder(approvedOrder));

    assertThat(exceptionThrown.getErrorType()).isEqualTo(ORDER_NOT_FOUND);

  }

}
