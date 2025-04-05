package com.example.demo.domain.model.value.object;

import com.example.demo.domain.exceptions.PriceIsNotPossitiveException;

public final class Money {
    private final Double value;

    public Money(Double value) {
        if (value == null || value < 0) {
            throw new PriceIsNotPossitiveException();
        }
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}
