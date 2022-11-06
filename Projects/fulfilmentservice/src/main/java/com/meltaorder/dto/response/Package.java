package com.meltaorder.dto.response;

import static com.meltaorder.dto.response.Products.INTERNET;
import static com.meltaorder.dto.response.Products.MOBILE;
import static com.meltaorder.dto.response.Products.TELEPHONY;
import static com.meltaorder.dto.response.Products.TV;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.stream.Stream;

public enum Package {

  INTERNET_250_MBPS(INTERNET,"250Mbps"),
  INTERNET_1_GBPS(INTERNET, "1Gbps"),

  TV_90(TV, "90 Channels"),
  TV_140(TV, "140 Channels"),

  TELEPHONY_FREE_ON_NET_CALLS(TELEPHONY, "Free On net Calls"),
  TELEPHONY_UNLIMITED_CALLS(TELEPHONY, "Unlimited Calls"),

  MOBILE_PREPAID(MOBILE, "Mobile Prepaid"),
  MOBILE_POST_PAID(MOBILE, "Mobile Post-paid");

  private Products productName;
  private String packageName;


  Package(Products productName, String packageName) {
    this.packageName = packageName;
    this.productName = productName;
  }

  @JsonValue
  public String getPackageName() {
    return packageName;
  }

  @JsonValue
  public Products getProductName() {
    return productName;
  }

  public static Package of(final Products productName) {
    return Stream.of(Package.values())
        .filter(paymentType -> paymentType.getProductName().equals(productName))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
