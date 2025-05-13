package com.example.demo.adapters.driven.jpa.postgresql.adapters;

import com.example.demo.adapters.driven.jpa.postgresql.mappers.IDefinitionAttributeEntityMapper;
import com.example.demo.adapters.driven.jpa.postgresql.repositories.IDefinitionAttributeRepository;
import com.example.demo.domain.model.value.object.DefinitionAttribute;
import com.example.demo.domain.spi.IDefinitionAttributePersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class DefinitionAttributesPostgresqlAdapter implements IDefinitionAttributePersistencePort {
    private final IDefinitionAttributeRepository definitionAttributeRepository;
    private final IDefinitionAttributeEntityMapper definitionAttributeEntityMapper;

    @Override
    public void save(DefinitionAttribute definitionAttribute) {
        definitionAttributeRepository.save(definitionAttributeEntityMapper.toDefinitionAttributesEntity(definitionAttribute));
    }

    @Override
    public Optional<DefinitionAttribute> findById(Long id) {
        return definitionAttributeRepository.findById(id)
                .map(definitionAttributeEntityMapper::toDefinitionAttributes);
    }
}
