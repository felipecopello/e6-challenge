package com.episode6;

import static org.junit.jupiter.api.Assertions.*;

import com.episode6.enums.TransactionTypeEnum;
import com.episode6.models.CreditCard;
import java.util.Collections;
import org.junit.jupiter.api.Test;

class CreditCardBuilderNullTest {

  @Test
  void shouldThrowIllegalStateExceptionWhenBalanceTransferFeeIsNull() {
    CreditCard.Builder builder =
        new CreditCard.Builder()
            .nickName("NullFeeCard")
            .creditLimit(5000.0)
            .latePaymentFee(25.0)
            .transactionTypesAllowed(Collections.singleton(TransactionTypeEnum.FEE))
            .balanceTransferEnabled(true);

    assertThrows(
        IllegalStateException.class,
        () -> builder.balanceTransferFee(null).build(),
        "Expected builder to throw IllegalStateExceptionException when balanceTransferFee is null");
  }

  @Test
  void shouldThrowIllegalStateExceptionWhenTransactionTypesAreNull() {
    CreditCard.Builder builder =
        new CreditCard.Builder()
            .nickName("NullTransactionsCard")
            .creditLimit(3000.0)
            .latePaymentFee(15.0)
            .balanceTransferEnabled(false);

    assertThrows(
        IllegalStateException.class,
        () -> builder.transactionTypesAllowed(null).build(),
        "Expected builder to throw IllegalStateExceptionException when transactionTypesAllowed is null");
  }

  @Test
  void shouldThrowIllegalStateExceptionWhenCreditLimitIsNull() {
    CreditCard.Builder builder =
        new CreditCard.Builder()
            .nickName("NullCreditLimit")
            .latePaymentFee(15.0)
            .balanceTransferEnabled(false)
            .transactionTypesAllowed(Collections.singleton(TransactionTypeEnum.FEE));

    assertThrows(
        IllegalStateException.class,
        () -> builder.creditLimit(null).build(),
        "Expected builder to throw IllegalStateExceptionException when creditLimit is null");
  }

  @Test
  void shouldThrowIllegalStateExceptionWhenLatePaymentFeetIsNull() {
    CreditCard.Builder builder =
        new CreditCard.Builder()
            .nickName("NullCreditLimit")
            .creditLimit(3000.0)
            .balanceTransferEnabled(false)
            .transactionTypesAllowed(Collections.singleton(TransactionTypeEnum.FEE));

    assertThrows(
        IllegalStateException.class,
        () -> builder.latePaymentFee(null).build(),
        "Expected builder to throw IllegalStateExceptionException when creditLimit is null");
  }
}
