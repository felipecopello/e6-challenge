package com.episode6.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class CreditCardDataProvider {

  public static Stream<Arguments> validCreditCardConfigs() {
    return loadFromJson("src/test/resources/data/credit-cards.json");
  }

  public static Stream<Arguments> invalidCreditCardConfigs() {
    return loadFromJson("src/test/resources/data/invalid-credit-cards.json");
  }

  private static Stream<Arguments> loadFromJson(String path) {
    ObjectMapper mapper =
        new ObjectMapper()
            .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    try {
      List<Map<String, Object>> data = mapper.readValue(new File(path), new TypeReference<>() {});
      return data.stream().map(Arguments::of);
    } catch (Exception e) {
      throw new RuntimeException("Failed to load JSON data: " + path, e);
    }
  }
}
