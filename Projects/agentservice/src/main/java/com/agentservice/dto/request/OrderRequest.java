package com.agentservice.dto.request;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OrderRequest {

  @NotNull
  private  PersonalDetails personalDetails;
  @NotNull
  private  InstallationAddress installationAddress;
  @NotNull
  private List<Packages> packagesList;

}
