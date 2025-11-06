package com.episode6.models;

import static com.episode6.services.CreditCardValidatorImpl.validate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditCard extends AbstractCreditCard {

  private CreditCard() {}

  public static class Builder extends AbstractCreditCard.Builder<Builder, CreditCard> {

    public Builder() {
      super(new CreditCard());
    }

    @Override
    public CreditCard build() {
      validate(instance);
      return instance;
    }
  }
}
