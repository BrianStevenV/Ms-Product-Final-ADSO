package com.example.demo.domain.spi;

import com.example.demo.domain.model.value.object.DefinitionAttribute;

import java.util.Optional;

public interface IDefinitionAttributePersistencePort {
    void save(DefinitionAttribute definitionAttribute);
    Optional<DefinitionAttribute> findById(Long id);
}
