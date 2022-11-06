package com.meltaorder.controller;

import static com.meltaorder.constant.Constants.POST_ORDER_APPROVE_URL;
import static com.meltaorder.constant.Constants.POST_ORDER_URL;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.meltaorder.adaptor.request.ApprovedOrder;
import com.meltaorder.dto.request.OrderRequest;
import com.meltaorder.services.OrderService;
import javax.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @PostMapping(value = POST_ORDER_URL + "/{message}")
  public HttpEntity<Void> postOrderMessage(
      @PathVariable(value = "message") @NotBlank String message) {
    orderService.sendMessage(message);

    return ResponseEntity.ok().build();
  }

  @PostMapping(value = POST_ORDER_APPROVE_URL)
  public HttpEntity<Void> postApproveOrder(@RequestBody ApprovedOrder approvedOrder) {
    orderService.approvedOrder(approvedOrder);

    return ResponseEntity.ok().build();
  }

}
