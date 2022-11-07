package com.agentservice.controller;

import static com.agentservice.constant.Constants.POST_ORDER_STATUS_URL;
import static com.agentservice.constant.Constants.POST_ORDER_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.agentservice.adaptor.request.ApprovedOrder;
import com.agentservice.dto.request.OrderRequest;
import com.agentservice.utils.JsonUtils;
import com.agentservice.utils.ObjectFactory;
import com.agentservice.services.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {

  @MockBean
  private OrderService orderService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void updateOrderApprovedStatus_ShouldReturnOK() throws Exception {
    ApprovedOrder approveStatus = ObjectFactory.buildApprovedOrder();
    this.mockMvc
        .perform(
            post(POST_ORDER_STATUS_URL)
                .content(JsonUtils.objectToJson(approveStatus))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void postOrder_ShouldReturnOK() throws Exception {
    OrderRequest orderRequest = ObjectFactory.buildOrderRequest();
    this.mockMvc
        .perform(
            post(POST_ORDER_URL)
                .content(JsonUtils.objectToJson(orderRequest))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}