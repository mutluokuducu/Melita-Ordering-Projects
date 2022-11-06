package com.meltaorder.mapper;

import static com.meltaorder.dto.response.ApproveStatus.Pending;

import com.meltaorder.dto.response.OrderResponse;
import com.meltaorder.dto.response.Packages;
import com.meltaorder.dto.response.Slot;
import com.meltaorder.repository.entity.InstallationAddress;
import com.meltaorder.repository.entity.PersonalDetails;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderMapper {
  public static PersonalDetails getPersonalDetails(OrderResponse orderResponse) {
    PersonalDetails personalDetails = PersonalDetails.builder()
        .fullName(orderResponse.getPersonalDetails().getFullName())
        .birthDate(orderResponse.getPersonalDetails().getBirthDate())
        .emailAddress(orderResponse.getPersonalDetails().getEmailAddress())
        .phoneNumber(orderResponse.getPersonalDetails().getPhoneNumber())
        .approveStatus(Pending)
        .localDateTime(LocalDateTime.now())
        .installationAddress(InstallationAddress.builder()
            .addressLine1(orderResponse.getInstallationAddress().getAddressLine1())
            .addressLine2(orderResponse.getInstallationAddress().getAddressLine2())
            .addressLine3(orderResponse.getInstallationAddress().getAddressLine3())
            .postCode(orderResponse.getInstallationAddress().getPostCode())
            .city(orderResponse.getInstallationAddress().getCity())
            .country(orderResponse.getInstallationAddress().getCountry())
            .slotList(getSlotLIst(orderResponse.getInstallationAddress().getSlotList()))
            .build())
        .packagesList(getPackageList(orderResponse.getPackagesList()))
        .build();
    personalDetails.getInstallationAddress().setPersonalDetails(personalDetails);

    personalDetails.getPackagesList().forEach(x -> x.setPersonalDetails(personalDetails));
    personalDetails.getInstallationAddress().getSlotList()
        .forEach(x -> x.setInstallationAddress(personalDetails.getInstallationAddress()));
    return personalDetails;
  }

  private List<com.meltaorder.repository.entity.Slot> getSlotLIst(List<Slot> slotList) {
    List<com.meltaorder.repository.entity.Slot> slots = new ArrayList<>();
    slotList.forEach(slot -> slots.add(com.meltaorder.repository.entity.Slot.builder()
        .installationDate(slot.getInstallationDate())
        .startTime(slot.getStartTime())
        .endTime(slot.getEndTime())
        .build()));
    return slots;
  }

  private List<com.meltaorder.repository.entity.Packages> getPackageList(
      List<Packages> packagesList) {
    List<com.meltaorder.repository.entity.Packages> packages = new ArrayList<>();
    packagesList.forEach(packagesDto -> packages.add(com.meltaorder.repository.entity.Packages.builder()
        .packageName(packagesDto.getPackageName())
        .productsName(packagesDto.getProductsName())
        .localDateTime(LocalDateTime.now())
        .build()));
    return packages;
  }
}
