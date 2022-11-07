package com.meltaorder.controller;

import static com.melitaorder.utils.JsonUtils.objectToJson;
import static com.melitaorder.utils.ObjectFactory.buildApprovedOrder;
import static com.meltaorder.constant.Constants.POST_ORDER_APPROVE_URL;
import static com.meltaorder.exeption.ErrorType.ORDER_NOT_FOUND;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.meltaorder.dto.request.ApprovedOrder;
import com.meltaorder.exeption.GlobalExceptionHandler;
import com.meltaorder.exeption.OrderServiceException;
import com.meltaorder.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

  private MockMvc mockMvc;

  @Mock
  private OrderService orderService;

  @InjectMocks
  private OrderController orderController;

  @BeforeEach
  public void init() {
    mockMvc =
        MockMvcBuilders.standaloneSetup(orderController)
            .setControllerAdvice(new GlobalExceptionHandler())
            .build();
  }

  @Test
  void updateOrderApprovedStatus_ShouldReturnOK() throws Exception {
    ApprovedOrder approveStatus = buildApprovedOrder();
    this.mockMvc
        .perform(
            post(POST_ORDER_APPROVE_URL)
                .content(objectToJson(approveStatus))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void updateOrderApprovedStatus_ShouldReturnNotFound_WhenIdIsWrong() throws Exception {
    ApprovedOrder approveStatus = buildApprovedOrder();
    approveStatus.setOrderId(2L);
    doThrow(new OrderServiceException(ORDER_NOT_FOUND))
        .when(orderService).approvedOrder(approveStatus);

    this.mockMvc
        .perform(
            post(POST_ORDER_APPROVE_URL)
                .content(objectToJson(approveStatus))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

}