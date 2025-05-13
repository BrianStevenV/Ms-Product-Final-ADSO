package com.example.demo.domain.usecase;

import com.example.demo.adapters.driving.http.handler.commands.CreateProductAttributeCommandRequest;
import com.example.demo.domain.api.IProductAttributeServicePort;
import com.example.demo.domain.model.Category;
import com.example.demo.domain.model.value.object.CategoryAttribute;
import com.example.demo.domain.model.value.object.DefinitionAttribute;
import com.example.demo.domain.spi.ICategoryAttributePersistencePort;
import com.example.demo.domain.spi.ICategoryPersistencePort;
import com.example.demo.domain.spi.IDefinitionAttributePersistencePort;
import java.util.Optional;

import java.util.List;

public class ProductAttributeUseCase implements IProductAttributeServicePort {
    private final IDefinitionAttributePersistencePort definitionPersistencePort;
    private final ICategoryAttributePersistencePort categoryAttributePersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;

    public ProductAttributeUseCase(
            IDefinitionAttributePersistencePort definitionPersistencePort,
            ICategoryAttributePersistencePort categoryAttributePersistencePort,
            ICategoryPersistencePort categoryPersistencePort) {
        this.definitionPersistencePort = definitionPersistencePort;
        this.categoryAttributePersistencePort = categoryAttributePersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void createProductAttribute(CreateProductAttributeCommandRequest productAttributeCommandRequest) {
        DefinitionAttribute definitionAttribute = new DefinitionAttribute(
                productAttributeCommandRequest.getName(),
                productAttributeCommandRequest.getType()
        );

        definitionPersistencePort.save(definitionAttribute);

        List<Category> categories = getCategories(productAttributeCommandRequest.getCategoriesId());

        categories.stream()
                .forEach(category -> {
                    categoryAttributePersistencePort.save(
                            new CategoryAttribute(
                                    category,
                                    definitionAttribute
                            )
                    );
                });
    }

    private List<Category> getCategories(List<Long> categoriesId){
        return categoriesId.stream()
                .map(categoryPersistencePort::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
}
