package com.meltaorder.dto.response;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstallationAddress {

  @NotEmpty
  private String addressLine1;
  private String addressLine2;
  private String addressLine3;
  @NotEmpty
  private String postCode;
  @NotEmpty
  private String city;
  @NotEmpty
  private String country;
  @NotEmpty
  private List<Slot> slotList;



}