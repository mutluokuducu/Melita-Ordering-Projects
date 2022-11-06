package com.meltaorder.dto.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class test {

  public static void main(String[] args) throws JsonProcessingException {
    OrderRequest orderRequest = OrderRequest.builder()
        .personalDetails(PersonalDetails.builder()
            .fullName("test")
            .phoneNumber("123456789")
            .birthDate(LocalDate.of(1990, 12, 11))
            .build())
        .installationAddress(InstallationAddress.builder()
            .addressLine1("test1")
            .addressLine2("test1")
            .city("Reading")
            .postCode("RG54HS")
            .country("UK")
            .slotList(Arrays.asList(
                Slot.builder()
                 //   .installationDate(LocalDate.now())
                    .startTime(LocalTime.of(12, 00, 0))
                    .endTime(LocalTime.of(16, 00, 0))
                    .build(),
                Slot.builder()
                  //  .installationDate(LocalDate.of(2022, 12, 11))
                    .startTime(LocalTime.of(12, 00, 0))
                    .endTime(LocalTime.of(16, 00, 0))
                    .build()))
            .build())
        .packagesList(Arrays.asList(
            Packages.builder()
                .productsName(Package.INTERNET_1_GBPS.getProductName())
                .packageName(Package.INTERNET_1_GBPS.getPackageName())
                .build(),
            Packages.builder()
                .productsName(Package.MOBILE_POST_PAID.getProductName())
                .packageName(Package.MOBILE_POST_PAID.getPackageName())
                .build(),
            Packages.builder()
                .productsName(Package.TELEPHONY_FREE_ON_NET_CALLS.getProductName())
                .packageName(Package.TELEPHONY_FREE_ON_NET_CALLS.getPackageName())
                .build()
        ))

        .build();
    ObjectMapper mapper = new ObjectMapper();
   // mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    String ss = mapper.writeValueAsString(orderRequest);
    System.out.println(ss);
  }

}
