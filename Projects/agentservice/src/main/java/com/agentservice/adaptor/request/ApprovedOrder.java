package com.agentservice.adaptor.request;

import com.agentservice.dto.ApproveStatus;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApprovedOrder {

  @NotEmpty
  private Long orderId;
  @NotNull
  private ApproveStatus approveStatus;
}
