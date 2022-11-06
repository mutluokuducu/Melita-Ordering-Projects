package com.meltaorder.adaptor.fulfilmentservice;

import static com.meltaorder.constant.Constants.POST_ORDER_APPROVE_URL;

import com.meltaorder.adaptor.request.ApprovedOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "${fulfilment.url}", name = "fulfilmentClient")
public interface FulfilmentClient {

  @PostMapping(
      value = POST_ORDER_APPROVE_URL, produces = "application/json")
  public void approvedOrder(ApprovedOrder approvedOrder);
}
