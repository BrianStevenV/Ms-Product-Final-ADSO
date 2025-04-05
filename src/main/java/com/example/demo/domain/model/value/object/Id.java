package com.example.demo.domain.model.value.object;

import java.util.Objects;

public final class Id {
    private final long value;

    public Id(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Id)) return false;
        Id userId = (Id) o;
        return value == userId.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
