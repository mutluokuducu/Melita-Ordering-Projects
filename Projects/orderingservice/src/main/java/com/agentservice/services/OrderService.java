package com.agentservice.services;

import com.agentservice.adaptor.request.ApprovedOrder;
import com.agentservice.dto.request.OrderRequest;

public interface OrderService {

  void sendMessage(OrderRequest orderRequest);

  void approvedOrder(ApprovedOrder approvedOrder);
}
