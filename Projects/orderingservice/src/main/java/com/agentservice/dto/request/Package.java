package com.agentservice.dto.request;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.stream.Stream;

public enum Package {

  INTERNET_250_MBPS(Products.INTERNET,"250Mbps"),
  INTERNET_1_GBPS(Products.INTERNET, "1Gbps"),

  TV_90(Products.TV, "90 Channels"),
  TV_140(Products.TV, "140 Channels"),

  TELEPHONY_FREE_ON_NET_CALLS(Products.TELEPHONY, "Free On net Calls"),
  TELEPHONY_UNLIMITED_CALLS(Products.TELEPHONY, "Unlimited Calls"),

  MOBILE_PREPAID(Products.MOBILE, "Mobile Prepaid"),
  MOBILE_POST_PAID(Products.MOBILE, "Mobile Post-paid");

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
