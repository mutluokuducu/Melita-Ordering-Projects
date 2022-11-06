package com.meltaorder.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Slot {

  @NotEmpty
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate installationDate;
  @NotEmpty
  @JsonFormat(pattern = "HH:mm:ss")
  private LocalTime startTime;
  @NotEmpty
  @JsonFormat(pattern = "HH:mm:ss")
  private LocalTime endTime;
}
