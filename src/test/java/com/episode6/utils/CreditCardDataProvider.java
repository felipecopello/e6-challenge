package com.episode6.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class CreditCardDataProvider {

  public static Stream<Arguments> validCreditCardConfigs() {
    return loadFromJson("data/credit-cards.json");
  }

  public static Stream<Arguments> invalidCreditCardConfigs() {
    return loadFromJson("data/invalid-credit-cards.json");
  }

  private static Stream<Arguments> loadFromJson(String path) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      InputStream input = CreditCardDataProvider.class.getClassLoader().getResourceAsStream(path);

      if (input == null) {
        throw new IllegalStateException("Could not find: " + path);
      }

      List<Map<String, Object>> configs = mapper.readValue(input, List.class);
      return configs.stream().map(Arguments::of);
    } catch (Exception e) {
      throw new RuntimeException("Failed to load JSON: " + path, e);
    }
  }
}
