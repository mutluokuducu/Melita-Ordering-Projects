package com.meltaorder.services;

import com.meltaorder.adaptor.request.ApprovedOrder;
import com.meltaorder.dto.request.OrderRequest;

public interface OrderService {

  void sendMessage(OrderRequest orderRequest);

  void sendMessage(String string);

  void approvedOrder(ApprovedOrder approvedOrder);
}
