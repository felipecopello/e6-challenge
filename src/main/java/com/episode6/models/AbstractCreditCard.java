package com.episode6.models;

import com.episode6.enums.TransactionTypeEnum;
import java.util.EnumSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractCreditCard {

  protected Double creditLimit = 5000.00;
  protected String nickName = "Default Card";
  protected Double latePaymentFee = 25.00;
  protected Set<TransactionTypeEnum> transactionTypesAllowed =
      EnumSet.of(TransactionTypeEnum.PURCHASE);
  protected boolean balanceTransferEnabled = false;
  protected Double balanceTransferFee = 0.0;

  public abstract static class Builder<T extends Builder<T, O>, O extends AbstractCreditCard> {
    protected final O instance;

    protected Builder(O instance) {
      this.instance = instance;
    }

    @SuppressWarnings("unchecked")
    public T creditLimit(Double creditLimit) {
      instance.setCreditLimit(creditLimit);
      return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T nickName(String nickName) {
      instance.setNickName(nickName);
      return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T latePaymentFee(Double latePaymentFee) {
      instance.setLatePaymentFee(latePaymentFee);
      return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T transactionTypesAllowed(Set<TransactionTypeEnum> types) {
      instance.setTransactionTypesAllowed(types);
      return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T balanceTransferEnabled(boolean enabled) {
      instance.setBalanceTransferEnabled(enabled);
      return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T balanceTransferFee(Double fee) {
      instance.setBalanceTransferFee(fee);
      return (T) this;
    }

    public abstract O build();
  }
}
