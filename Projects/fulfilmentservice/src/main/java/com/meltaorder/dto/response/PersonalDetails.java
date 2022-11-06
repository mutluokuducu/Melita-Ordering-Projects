package com.meltaorder.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonalDetails {

  @NotBlank
  @Pattern(regexp = "^$|[A-Za-z0-9-/.&\\- ]{1,100}", message = "Invalid full name")
  private String fullName;

  @NotBlank
  @Pattern(regexp = "^[1-9]\\d*$", message = "Invalid ")
  @Size(max = 10, min = 10, message = "Invalid  phone number")
  private String phoneNumber;

  @NotBlank
  private  String  emailAddress;

  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonFormat(shape= Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate birthDate;

}