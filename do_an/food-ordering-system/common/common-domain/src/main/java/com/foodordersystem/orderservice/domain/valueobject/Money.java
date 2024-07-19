package com.foodordersystem.orderservice.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Money {
    public static final Money ZERO = new Money(BigDecimal.ZERO);

    private final BigDecimal amount;

    public boolean isGreaterThanZero() {
        return this.amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isGreaterThan(Money money){
        return this.amount.compareTo(money.getAmount()) > 0;
    }

    public Money add(Money money){
        return new Money(setScale(this.amount.add(money.getAmount())));
    }

    public Money subtract(Money money){
        return new Money(setScale(this.amount.subtract(money.getAmount())));
    }

    public Money multiply(int multiplier){
        return new Money(setScale(this.amount.multiply(new BigDecimal(multiplier))));
    }

    private BigDecimal setScale(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.HALF_EVEN);
    }
}
