package com.episode6.utils;

import com.episode6.enums.TransactionTypeEnum;
import com.episode6.models.CreditCard;
import java.util.Set;

public class CreditCardValidator {

  public static void validate(CreditCard card) {
    if (card == null) {
      throw new IllegalStateException("Credit card cannot be null");
    }

    if (card.getCreditLimit() == null || card.getCreditLimit() <= 0) {
      throw new IllegalStateException("Credit limit must be a positive number");
    }

    if (card.getNickName() == null || card.getNickName().isBlank()) {
      throw new IllegalStateException("Nickname cannot be null or empty");
    }

    if (card.getLatePaymentFee() == null || card.getLatePaymentFee() < 0) {
      throw new IllegalStateException("Late payment fee cannot be null or negative");
    }

    validateTransactionType(card);
    validateBalanceTransfer(card);
  }

  private static void validateTransactionType(CreditCard card) {
    Set<TransactionTypeEnum> types = card.getTransactionTypesAllowed();
    if (types == null || types.isEmpty()) {
      throw new IllegalStateException("At least one transaction type must be allowed");
    }
  }

  private static void validateBalanceTransfer(CreditCard card) {
    Boolean enabled = card.isBalanceTransferEnabled();
    Double fee = card.getBalanceTransferFee();

    if (enabled == null) {
      throw new IllegalStateException("Balance transfer enabled flag cannot be null");
    }

    if (enabled) {
      if (fee == null || fee <= 0) {
        throw new IllegalStateException(
            "Balance transfer fee must be positive when balance transfer is enabled");
      }
    } else {
      if (fee != null && fee > 0) {
        throw new IllegalStateException(
            "Balance transfer fee must be 0 or null when balance transfer is disabled");
      }
    }
  }
}
