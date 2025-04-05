package com.example.demo.domain.model.value.object;

public final class Discount {
    private final Id id;
    private final Double percentage;

    public Discount(Id id, Double percentage) {
        this.id = id;
        this.percentage = percentage;
    }

    public Id getId() {
        return id;
    }

    public Double getPercentage() {
        return percentage;
    }
}
