package com.example.demo.domain.model.value.object;

public final class Inventory {
    private final Integer quantity;
    private final Boolean isActive;
    private final Integer value;

    public Inventory(Integer quantity, Boolean isActive, Integer value) {
        validationQuantityMoreThanZero(quantity);
        this.quantity = quantity;
        this.isActive = true;
        this.value = value;
    }

    public Inventory(Integer quantity, Boolean isActive) {
        validationQuantityMoreThanZero(quantity);
        this.quantity = quantity;
        this.isActive = true;
        this.value = null;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public Integer getValue() {
        return value;
    }

    public Inventory add(Integer quantity) {
        if(quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        return new Inventory(this.quantity + quantity, this.isActive);
    }

//    public Inventory subtract(Integer quantity) {
//        if(quantity < 0) {
//            throw new IllegalArgumentException("Quantity cannot be negative");
//        }
//        if(this.quantity - quantity < 0) {
//            throw new IllegalArgumentException("Resulting quantity cannot be negative");
//        }
//        return new Inventory(this.quantity - quantity, this.isActive);
//    }
    public Inventory subtract(Integer quantity, Integer value) {
        if(quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if(quantity - value < 0) {
            throw new IllegalArgumentException("Resulting quantity cannot be negative");
        }
        return new Inventory(this.quantity - quantity, this.isActive);
    }

    public Inventory disable() {
        return new Inventory(this.quantity, false);
    }

    private void validationQuantityMoreThanZero(Integer quantity) {
        if(quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
    }
}
