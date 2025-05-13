package com.example.demo.domain.usecase;

import com.example.demo.adapters.driving.http.handler.commands.CreateProductAttributeCommandRequest;
import com.example.demo.domain.model.Category;
import com.example.demo.domain.model.value.object.CategoryAttribute;
import com.example.demo.domain.model.value.object.CreationDate;
import com.example.demo.domain.model.value.object.DefinitionAttribute;
import com.example.demo.domain.model.value.object.Id;
import com.example.demo.domain.spi.ICategoryAttributePersistencePort;
import com.example.demo.domain.spi.ICategoryPersistencePort;
import com.example.demo.domain.spi.IDefinitionAttributePersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@TestPropertySource(locations = "classpath:application-dev.yml")
@SpringBootTest
class ProductAttributeUseCaseTest {

    @Mock
    private IDefinitionAttributePersistencePort definitionPersistencePort;

    @Mock
    private ICategoryAttributePersistencePort categoryAttributePersistencePort;

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    private ProductAttributeUseCase productAttributeUseCase;

    @Captor
    private ArgumentCaptor<DefinitionAttribute> definitionAttributeCaptor;

    @Captor
    private ArgumentCaptor<CategoryAttribute> categoryAttributeCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productAttributeUseCase = new ProductAttributeUseCase(
                definitionPersistencePort,
                categoryAttributePersistencePort,
                categoryPersistencePort
        );
    }

    @Test
    void createProductAttributeSavesDefinitionAttributeAndCategoryAttributes() {
        // Arrange
        CreateProductAttributeCommandRequest request = new CreateProductAttributeCommandRequest(
                "Color",
                "TEXT",
                List.of(1L, 2L)
        );

        Category category1 = new Category(new Id(1L), "Electronics", "Electronics", true, CreationDate.builderCreationDate(), null, null );
        Category category2 = new Category(new Id(2L), "Clothing", "Clothing", true, CreationDate.builderCreationDate(), null, null );


        when(categoryPersistencePort.findById(1L)).thenReturn(Optional.of(category1));
        when(categoryPersistencePort.findById(2L)).thenReturn(Optional.of(category2));

        // Act
        productAttributeUseCase.createProductAttribute(request);

        // Assert
        verify(definitionPersistencePort, times(1)).save(definitionAttributeCaptor.capture());
        DefinitionAttribute savedDefinitionAttribute = definitionAttributeCaptor.getValue();
        assertEquals("Color", savedDefinitionAttribute.getNameAttribute());
        assertEquals("TEXT", savedDefinitionAttribute.getTypeDataOfValueAttribute());

        verify(categoryAttributePersistencePort, times(2)).save(categoryAttributeCaptor.capture());
        List<CategoryAttribute> savedCategoryAttributes = categoryAttributeCaptor.getAllValues();

        assertEquals(category1, savedCategoryAttributes.get(0).getCategory());
        assertEquals(savedDefinitionAttribute, savedCategoryAttributes.get(0).getDefinitionAttribute());

        assertEquals(category2, savedCategoryAttributes.get(1).getCategory());
        assertEquals(savedDefinitionAttribute, savedCategoryAttributes.get(1).getDefinitionAttribute());
    }

    @Test
    void createProductAttributeSavesDefinitionAttributeAndIgnoresNonExistingCategories() {
        // Arrange
        CreateProductAttributeCommandRequest request = new CreateProductAttributeCommandRequest(
                "Size",
                "NUMBER",
                List.of(1L, 99L, 2L) // 99L is a non-existing category
        );

        Category category1 = new Category(new Id(1L), "Electronics", "Electronics", true, CreationDate.builderCreationDate(), null, null );
        Category category2 = new Category(new Id(2L), "Clothing", "Clothing", true, CreationDate.builderCreationDate(), null, null );


        when(categoryPersistencePort.findById(1L)).thenReturn(Optional.of(category1));
        when(categoryPersistencePort.findById(99L)).thenReturn(Optional.empty());
        when(categoryPersistencePort.findById(2L)).thenReturn(Optional.of(category2));

        // Act
        productAttributeUseCase.createProductAttribute(request);

        // Assert
        verify(definitionPersistencePort, times(1)).save(definitionAttributeCaptor.capture());
        DefinitionAttribute savedDefinitionAttribute = definitionAttributeCaptor.getValue();
        assertEquals("Size", savedDefinitionAttribute.getNameAttribute());
        assertEquals("NUMBER", savedDefinitionAttribute.getTypeDataOfValueAttribute());

        verify(categoryAttributePersistencePort, times(2)).save(categoryAttributeCaptor.capture());
        List<CategoryAttribute> savedCategoryAttributes = categoryAttributeCaptor.getAllValues();

        assertEquals(category1, savedCategoryAttributes.get(0).getCategory());
        assertEquals(savedDefinitionAttribute, savedCategoryAttributes.get(0).getDefinitionAttribute());

        assertEquals(category2, savedCategoryAttributes.get(1).getCategory());
        assertEquals(savedDefinitionAttribute, savedCategoryAttributes.get(1).getDefinitionAttribute());
    }

    @Test
    void createProductAttributeSavesDefinitionAttributeEvenWithNoCategories() {
        // Arrange
        CreateProductAttributeCommandRequest request = new CreateProductAttributeCommandRequest(
                "Material",
                "TEXT",
                List.of()
        );

        // Act
        productAttributeUseCase.createProductAttribute(request);

        // Assert
        verify(definitionPersistencePort, times(1)).save(definitionAttributeCaptor.capture());
        DefinitionAttribute savedDefinitionAttribute = definitionAttributeCaptor.getValue();
        assertEquals("Material", savedDefinitionAttribute.getNameAttribute());
        assertEquals("TEXT", savedDefinitionAttribute.getTypeDataOfValueAttribute());

        verify(categoryAttributePersistencePort, times(0)).save(any(CategoryAttribute.class));
    }
}