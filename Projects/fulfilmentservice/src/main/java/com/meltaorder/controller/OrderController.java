package com.meltaorder.controller;

import static com.meltaorder.constant.Constants.POST_ORDER_APPROVE_URL;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.meltaorder.dto.request.ApprovedOrder;
import com.meltaorder.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController {

  @Autowired
  public OrderService orderService;

  @PostMapping(value = POST_ORDER_APPROVE_URL, consumes = APPLICATION_JSON_VALUE)
  public HttpEntity<Void> postOrder(@RequestBody ApprovedOrder approvedOrder) {
    orderService.approvedOrder(approvedOrder);

    return ResponseEntity.ok().build();
  }
}

