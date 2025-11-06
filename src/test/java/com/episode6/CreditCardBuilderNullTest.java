package com.episode6;

import static org.junit.jupiter.api.Assertions.*;

import com.episode6.enums.TransactionTypeEnum;
import com.episode6.exceptions.InvalidParameterException;
import com.episode6.models.CreditCard;
import java.util.Collections;
import org.junit.jupiter.api.Test;

class CreditCardBuilderNullTest {

  @Test
  void shouldThrowInvalidParameterExceptionWhenBalanceTransferFeeIsNull() {
    CreditCard.Builder builder =
        new CreditCard.Builder()
            .nickName("NullFeeCard")
            .creditLimit(5000.0)
            .latePaymentFee(25.0)
            .transactionTypesAllowed(Collections.singleton(TransactionTypeEnum.FEE))
            .balanceTransferEnabled(true);

    assertThrows(
        InvalidParameterException.class,
        () -> builder.balanceTransferFee(null).build(),
        "Expected builder to throw InvalidParameterException when balanceTransferFee is null");
  }

  @Test
  void shouldThrowInvalidParameterExceptionWhenTransactionTypesAreNull() {
    CreditCard.Builder builder =
        new CreditCard.Builder()
            .nickName("NullTransactionsCard")
            .creditLimit(3000.0)
            .latePaymentFee(15.0)
            .balanceTransferEnabled(false);

    assertThrows(
        InvalidParameterException.class,
        () -> builder.transactionTypesAllowed(null).build(),
        "Expected builder to throw InvalidParameterException when transactionTypesAllowed is null");
  }

  @Test
  void shouldThrowInvalidParameterExceptionWhenCreditLimitIsNull() {
    CreditCard.Builder builder =
        new CreditCard.Builder()
            .nickName("NullCreditLimit")
            .latePaymentFee(15.0)
            .balanceTransferEnabled(false)
            .transactionTypesAllowed(Collections.singleton(TransactionTypeEnum.FEE));

    assertThrows(
        InvalidParameterException.class,
        () -> builder.creditLimit(null).build(),
        "Expected builder to throw InvalidParameterException when creditLimit is null");
  }

  @Test
  void shouldThrowInvalidParameterExceptionWhenLatePaymentFeetIsNull() {
    CreditCard.Builder builder =
        new CreditCard.Builder()
            .nickName("NullCreditLimit")
            .creditLimit(3000.0)
            .balanceTransferEnabled(false)
            .transactionTypesAllowed(Collections.singleton(TransactionTypeEnum.FEE));

    assertThrows(
        InvalidParameterException.class,
        () -> builder.latePaymentFee(null).build(),
        "Expected builder to throw InvalidParameterException when creditLimit is null");
  }
}
