package com.agentservice.utils;

import com.agentservice.adaptor.request.ApprovedOrder;
import com.agentservice.dto.ApproveStatus;
import com.agentservice.dto.request.InstallationAddress;
import com.agentservice.dto.request.OrderRequest;
import com.agentservice.dto.request.Packages;
import com.agentservice.dto.request.PersonalDetails;
import com.agentservice.dto.request.Products;
import com.agentservice.dto.request.Slot;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectFactory {

  public static ApprovedOrder buildApprovedOrder() {
    return ApprovedOrder.builder()
        .orderId(1L)
        .approveStatus(ApproveStatus.Approved)
        .build();

  }

  public static OrderRequest buildOrderRequest() {
    return OrderRequest.builder()
        .installationAddress(InstallationAddress.builder()
            .addressLine1("line1")
            .addressLine2("line2")
            .postCode("rgt45")
            .city("reading")
            .country("uk")
            .slotList(Arrays.asList(Slot.builder()
                .installationDate(LocalDate.of(2022, 12, 13))
                .startTime(LocalTime.of(12, 00, 00))
                .endTime(LocalTime.of(12, 00, 00))
                .build()))
            .build())
        .personalDetails(PersonalDetails.builder()
            .fullName("test x")
            .birthDate(LocalDate.of(2000, 11, 11))
            .emailAddress("test@test.com")
            .phoneNumber("123456789")
            .build())
        .packagesList(Arrays.asList(Packages.builder()
            .packageName("250Mbps")
            .productsName(Products.INTERNET)
            .build()))
        .build();
  }

  public static PersonalDetails buildPersonalDetails() {
    return PersonalDetails.builder()
        .fullName("test")
        .birthDate(LocalDate.of(2000, 11, 11))
        .emailAddress("test@test.com")
        .phoneNumber("123456789")
        .build();
  }

  private static List<Slot> getSlotLIst() {
    List<Slot> slots = new ArrayList<>();
    slots.add(Slot.builder()
        .installationDate(LocalDate.of(2022, 12, 13))
        .startTime(LocalTime.of(12, 00, 00))
        .endTime(LocalTime.of(12, 00, 00))
        .build());
    return slots;
  }

  private static List<Packages> getPackageList() {
    List<Packages> packages = new ArrayList<>();
    packages.add(Packages.builder()
        .packageName("250Mbps")
        .productsName(Products.INTERNET)
        .build());
    return packages;
  }
}

