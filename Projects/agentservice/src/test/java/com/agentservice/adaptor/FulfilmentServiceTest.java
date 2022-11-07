package com.agentservice.adaptor;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.agentservice.constant.Constants.POST_ORDER_STATUS_URL;

import com.agentservice.adaptor.fulfilmentservice.FulfilmentClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 9091)
class FulfilmentServiceTest {

  @Autowired
  FulfilmentClient fulfilmentClient;

  @Test
  void postOrderStatus_whenValidClient_returnValidResponse() throws Exception {
    stubFor(post(urlEqualTo(POST_ORDER_STATUS_URL))
        .willReturn(aResponse()
            .withStatus(HttpStatus.OK.value())));
//            .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//            .withBody(read("stubs/posts.json"))));

  }

}
