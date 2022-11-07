package com.agentservice.adaptor.fulfilmentservice;

import static com.agentservice.constant.Constants.POST_ORDER_STATUS_URL;

import com.agentservice.adaptor.request.ApprovedOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${fulfilment.url}", name = "fulfilmentClient")
public interface FulfilmentClient {

  @PostMapping(
      value = POST_ORDER_STATUS_URL, produces = "application/json")
  void approvedOrder(ApprovedOrder approvedOrder);
}
