package com.agentservice.controller;

import static com.agentservice.constant.Constants.POST_ORDER_STATUS_URL;
import static com.agentservice.constant.Constants.POST_ORDER_URL;

import com.agentservice.adaptor.request.ApprovedOrder;
import com.agentservice.dto.request.OrderRequest;
import com.agentservice.services.OrderService;
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


  @PostMapping(value = POST_ORDER_URL)
  public HttpEntity<Void> postOrder(@RequestBody OrderRequest orderRequest) {
    orderService.sendMessage(orderRequest);

    return ResponseEntity.ok().build();
  }

  @PostMapping(value = POST_ORDER_STATUS_URL)
  public HttpEntity<Void> postApproveOrder(@RequestBody ApprovedOrder approvedOrder) {
    orderService.approvedOrder(approvedOrder);

    return ResponseEntity.ok().build();
  }

}
