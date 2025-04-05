package com.example.demo.domain.model.value.object;

import com.example.demo.domain.exceptions.ModificationDateAfterCreationDateException;

import java.time.LocalDateTime;

public final class ModificationDate extends AbstractDate {
    public ModificationDate(LocalDateTime value, CreationDate creationDate) {
        super(value, creationDate);
        if (!isAfterCreationDate(value)) {
            throw new ModificationDateAfterCreationDateException();
        }
    }
}
