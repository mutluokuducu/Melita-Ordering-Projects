package com.meltaorder.services;

import static com.meltaorder.exeption.ErrorType.ORDER_NOT_FOUND;

import com.meltaorder.dto.request.ApprovedOrder;
import com.meltaorder.exeption.OrderServiceException;
import com.meltaorder.repository.OrderingRepository;
import com.meltaorder.repository.entity.PersonalDetails;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

  @Autowired
  private OrderingRepository orderingRepository;

  private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

  public void approvedOrder(ApprovedOrder approvedOrder) {

    Optional<PersonalDetails> personalDetails = Optional.ofNullable(orderingRepository
        .findById(approvedOrder.getOrderId())
        .orElseThrow(() -> new OrderServiceException(ORDER_NOT_FOUND)));
    if (personalDetails.isPresent()) {
      personalDetails.get().setApproveStatus(approvedOrder.getApproveStatus());
      personalDetails.get().setLocalDateTime(LocalDateTime.now());
      orderingRepository.save(personalDetails.get());
    }
  }
}
