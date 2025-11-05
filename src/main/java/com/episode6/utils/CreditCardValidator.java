package com.episode6.utils;

import com.episode6.enums.Enums.TransactionType;
import com.episode6.models.CreditCard;

public class CreditCardValidator {
  private CreditCardValidator() {}

  public static void validate(CreditCard card) {
    if (card.getCreditLimit() <= 0) {
      throw new IllegalStateException("Credit limit must be positive");
    }

    if (card.getLatePaymentFee() < 0) {
      throw new IllegalStateException("Late payment fee cannot be negative");
    }

    validateTransactionType(card);
    validateBalanceTransfer(card);
  }

  private static void validateTransactionType(CreditCard card){
    if (card.getTransactionTypesAllowed() == null
        || card.getTransactionTypesAllowed().isEmpty()) {
      throw new IllegalStateException("At least one transaction type must be allowed");
    }

    for (TransactionType type : card.getTransactionTypesAllowed()) {
      boolean valid = false;
      for (TransactionType validType : TransactionType.values()) {
        if (validType == type) {
          valid = true;
          break;
        }
      }
      if (!valid) {
        throw new IllegalStateException("Invalid transaction type detected: " + type);
      }
    }
  }

  private static void validateBalanceTransfer(CreditCard card){
    if (card.isBalanceTransferEnabled()) {
      if (card.getBalanceTransferFee() <= 0) {
        throw new IllegalStateException(
            "Balance transfer fee must be positive when balance transfer is enabled");
      }
    } else {
      if (card.getBalanceTransferFee() > 0) {
        throw new IllegalStateException(
            "Balance transfer fee must be 0 when balance transfer is disabled");
      }
    }
  }
}
