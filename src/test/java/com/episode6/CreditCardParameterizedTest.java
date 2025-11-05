package com.episode6;

import static org.junit.jupiter.api.Assertions.*;

import com.episode6.enums.Enums.TransactionType;
import com.episode6.models.CreditCard;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class CreditCardParameterizedTest {

  @ParameterizedTest(name = "Validating card #{index}")
  @MethodSource("com.episode6.utils.CreditCardDataProvider#validCreditCardConfigs")
  void testCreditCardConfigurations(Map<String, Object> data) {
    @SuppressWarnings("unchecked")
    Set<TransactionType> types =
        ((List<String>) data.get("transactionTypesAllowed"))
            .stream()
                .map(String::toUpperCase)
                .map(TransactionType::valueOf)
                .collect(Collectors.toCollection(() -> EnumSet.noneOf(TransactionType.class)));

    CreditCard card =
        new CreditCard.Builder()
            .nickName((String) data.get("nickName"))
            .creditLimit(((Number) data.get("creditLimit")).doubleValue())
            .latePaymentFee(((Number) data.get("latePaymentFee")).doubleValue())
            .transactionTypesAllowed(types)
            .balanceTransferEnabled((Boolean) data.get("balanceTransferEnabled"))
            .balanceTransferFee(((Number) data.get("balanceTransferFee")).doubleValue())
            .build();

    assertEquals(data.get("nickName"), card.getNickName());
    assertEquals(((Number) data.get("creditLimit")).doubleValue(), card.getCreditLimit(), 0.001);
    assertEquals(
        ((Number) data.get("latePaymentFee")).doubleValue(), card.getLatePaymentFee(), 0.001);
    assertEquals(data.get("balanceTransferEnabled"), card.isBalanceTransferEnabled());
    assertEquals(
        (Boolean) data.get("balanceTransferEnabled")
            ? ((Number) data.get("balanceTransferFee")).doubleValue()
            : 0.0,
        card.getBalanceTransferFee(),
        0.001);
    assertEquals(types, card.getTransactionTypesAllowed());
  }

  @ParameterizedTest(name = "Invalid configuration should throw exception: {0}")
  @MethodSource("com.episode6.utils.CreditCardDataProvider#invalidCreditCardConfigs")
  void shouldThrowExceptionForInvalidConfigurations(Map<String, Object> config) {
    String nickName = (String) config.get("nickName");
    double creditLimit = ((Number) config.get("creditLimit")).doubleValue();
    double lateFee = ((Number) config.get("latePaymentFee")).doubleValue();
    boolean transferEnabled = (boolean) config.get("balanceTransferEnabled");
    double transferFee = ((Number) config.get("balanceTransferFee")).doubleValue();
    List<String> typeStrings = (List<String>) config.get("transactionTypesAllowed");

    assertThrows(
        Exception.class,
        () -> {
          //either fails trying to instantiate an invalid ENUM
          Set<TransactionType> transactionTypes = typeStrings.stream()
              .map(s -> TransactionType.valueOf(s.toUpperCase().trim()))
              .collect(Collectors.toSet());

          //or it fails in the builder
          new CreditCard.Builder()
              .nickName(nickName)
              .creditLimit(creditLimit)
              .latePaymentFee(lateFee)
              .transactionTypesAllowed(transactionTypes)
              .balanceTransferEnabled(transferEnabled)
              .balanceTransferFee(transferFee)
              .build();
        },
        () -> "Expected build() to fail for invalid config: " + nickName);
  }
}
