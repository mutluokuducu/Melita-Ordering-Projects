package com.meltaorder.utils;

import static com.meltaorder.dto.response.ApproveStatus.Approved;
import static com.meltaorder.dto.response.ApproveStatus.Pending;
import static com.meltaorder.dto.response.Products.INTERNET;

import com.meltaorder.dto.request.ApprovedOrder;
import com.meltaorder.dto.response.OrderResponse;
import com.meltaorder.dto.response.Packages;
import com.meltaorder.dto.response.Slot;
import com.meltaorder.repository.entity.InstallationAddress;
import com.meltaorder.repository.entity.PersonalDetails;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectFactory {

  public static ApprovedOrder buildApprovedOrder(){
    return ApprovedOrder.builder()
        .orderId(1L)
        .approveStatus(Approved)
        .build();

  }

  public static OrderResponse buildOrderResponse() {
    return OrderResponse.builder()
        .installationAddress(com.meltaorder.dto.response.InstallationAddress.builder()
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
        .personalDetails(com.meltaorder.dto.response.PersonalDetails.builder()
            .fullName("test")
            .birthDate(LocalDate.of(2000, 11, 11))
            .emailAddress("test@test.com")
            .phoneNumber("123456789")
            .build())
        .packagesList(Arrays.asList(Packages.builder()
            .packageName("250Mbps")
            .productsName(INTERNET)
            .build()))
        .build();
  }

  public static PersonalDetails buildPersonalDetails() {
    return PersonalDetails.builder()
        .fullName("test")
        .birthDate(LocalDate.of(2000, 11, 11))
        .emailAddress("test@test.com")
        .phoneNumber("123456789")
        .approveStatus(Pending)
        .installationAddress(InstallationAddress.builder()
            .addressLine1("line1")
            .addressLine2("line2")
            .postCode("rgt45")
            .city("reading")
            .country("uk")
            .slotList(getSlotLIst())
            .build())
        .packagesList(getPackageList())
        .build();
  }

  private static List<com.meltaorder.repository.entity.Slot> getSlotLIst() {
    List<com.meltaorder.repository.entity.Slot> slots = new ArrayList<>();
    slots.add(com.meltaorder.repository.entity.Slot.builder()
        .installationDate(LocalDate.of(2022, 12, 13))
        .startTime(LocalTime.of(12, 00, 00))
        .endTime(LocalTime.of(12, 00, 00))
        .build());
    return slots;
  }

  private static List<com.meltaorder.repository.entity.Packages> getPackageList() {
    List<com.meltaorder.repository.entity.Packages> packages = new ArrayList<>();
    packages.add(com.meltaorder.repository.entity.Packages.builder()
        .packageName("250Mbps")
        .productsName(INTERNET)
        .build());
    return packages;
  }
}

