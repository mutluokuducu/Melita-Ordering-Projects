package com.agentservice.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

public class JsonUtils {


  private static String JSON = null;


  private JsonUtils() {
    throw new IllegalStateException("Utility class");
  }

  public static String objectToJson(Object jsonObject) {

    if (jsonObject == null) {
      JSON = "Null Object";
    } else {
      try {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JSR310Module());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        JSON = objectMapper.writeValueAsString(jsonObject);
      } catch (Exception e) {
        JSON = "Object could not be converted to Json Format";
      }
    }
    return JSON;
  }
}