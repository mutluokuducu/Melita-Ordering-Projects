package com.meltaorder.controller;

import static com.meltaorder.constant.Constants.POST_ORDER_APPROVE_URL;
import static com.meltaorder.exeption.ErrorType.ORDER_NOT_FOUND;
import static com.meltaorder.utils.JsonUtils.objectToJson;
import static com.meltaorder.utils.ObjectFactory.buildApprovedOrder;
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

//  @Test
//  void createCreditCard_shouldGetCardList() throws Exception {
//    when(creditCardService
//        .getCardList())
//        .thenReturn(buildCreditCardResponse());
//
//    this.mockMvc
//        .perform(
//            get(CREDIT_CARD_URL)
//                .contentType(APPLICATION_JSON_UTF8_VALUE)
//        ).andExpect(status().isOk())
//        .andExpect(jsonPath("$.creditCardList").isNotEmpty())
//        .andExpect(jsonPath("$.creditCardList[0].fullName").value(NAME_1))
//        .andExpect(jsonPath("$.creditCardList[0].cardNumber").value(VALID_CARD_1))
//
//        .andExpect(jsonPath("$.creditCardList[1].fullName").value(NAME_2))
//        .andExpect(jsonPath("$.creditCardList[1].cardNumber").value(VALID_CARD_2));
//  }
}