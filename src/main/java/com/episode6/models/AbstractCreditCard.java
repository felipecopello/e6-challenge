package com.episode6.models;

import com.episode6.enums.Enums.TransactionType;
import java.util.EnumSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractCreditCard {

  protected double creditLimit = 5000.00;
  protected String nickName = "Default Card";
  protected double latePaymentFee = 25.00;
  protected Set<TransactionType> transactionTypesAllowed = EnumSet.of(TransactionType.PURCHASE);
  protected boolean balanceTransferEnabled = false;
  protected double balanceTransferFee = 0.0;

  @Override
  public String toString() {
    return this.getClass().getSimpleName()
        + "{"
        + "creditLimit="
        + creditLimit
        + ", nickName='"
        + nickName
        + '\''
        + ", latePaymentFee="
        + latePaymentFee
        + ", transactionTypesAllowed="
        + transactionTypesAllowed
        + ", balanceTransferEnabled="
        + balanceTransferEnabled
        + ", balanceTransferFee="
        + balanceTransferFee
        + '}';
  }

  public abstract static class Builder<T extends Builder<T, O>, O extends AbstractCreditCard> {
    protected final O instance;

    protected Builder(O instance) {
      this.instance = instance;
    }

    @SuppressWarnings("unchecked")
    public T creditLimit(double creditLimit) {
      instance.setCreditLimit(creditLimit);
      return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T nickName(String nickName) {
      instance.setNickName(nickName);
      return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T latePaymentFee(double latePaymentFee) {
      instance.setLatePaymentFee(latePaymentFee);
      return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T transactionTypesAllowed(Set<TransactionType> types) {
      instance.setTransactionTypesAllowed(types);
      return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T balanceTransferEnabled(boolean enabled) {
      instance.setBalanceTransferEnabled(enabled);
      return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T balanceTransferFee(double fee) {
      instance.setBalanceTransferFee(fee);
      return (T) this;
    }

    public abstract O build();
  }
}
