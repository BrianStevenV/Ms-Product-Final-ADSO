package com.example.demo.domain.usecase;

import com.example.demo.domain.api.ICategoryServicePort;
import com.example.demo.domain.model.Category;
import com.example.demo.domain.model.value.object.CategoryAttribute;
import com.example.demo.domain.model.value.object.CreationDate;
import com.example.demo.domain.model.value.object.DefinitionAttribute;
import com.example.demo.domain.model.value.object.Id;
import com.example.demo.domain.spi.ICategoryAttributePersistencePort;
import com.example.demo.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@TestPropertySource(locations = "classpath:application-dev.yml")
@SpringBootTest
class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @Mock
    private ICategoryAttributePersistencePort categoryAttributePersistencePort;

    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryUseCase = new CategoryUseCase(categoryPersistencePort, categoryAttributePersistencePort);
    }

    @Test
    void getAllCategoriesReturnsOnlyActiveCategories() {
        // Arrange
        Category activeCategory1 = new Category(new Id(1L), "Electronics", "Electronics", false, CreationDate.builderCreationDate(), null, null );
        activeCategory1.setIsActive(true);
        Category inactiveCategory = new Category(new Id(2L), "Books", "Books", true, CreationDate.builderCreationDate(), null, null );
        inactiveCategory.setIsActive(false);
        Category activeCategory2 = new Category(new Id(3L), "Clothing", "Clothing", false, CreationDate.builderCreationDate(), null, null );
        activeCategory2.setIsActive(true);

        List<Category> allCategories = List.of(activeCategory1, inactiveCategory, activeCategory2);
        when(categoryPersistencePort.findAll()).thenReturn(allCategories);

        List<Category> expectedActiveCategories = List.of(activeCategory1, activeCategory2);

        // Act
        List<Category> actualActiveCategories = categoryUseCase.getAllCategories();

        // Assert
        assertEquals(expectedActiveCategories.size(), actualActiveCategories.size());
        assertEquals(expectedActiveCategories.get(0).getId(), actualActiveCategories.get(0).getId());
        assertEquals(expectedActiveCategories.get(0).getTitle(), actualActiveCategories.get(0).getTitle());
        assertEquals(expectedActiveCategories.get(1).getId(), actualActiveCategories.get(1).getId());
        assertEquals(expectedActiveCategories.get(1).getTitle(), actualActiveCategories.get(1).getTitle());
    }

    @Test
    void getAllCategoriesReturnsEmptyListWhenNoActiveCategoriesExist() {
        // Arrange
        Category inactiveCategory1 = new Category(new Id(1L), "Electronics", "Electronics", false, CreationDate.builderCreationDate(), null, null );
        Category inactiveCategory2 = new Category(new Id(2L), "Clothing", "Clothing", false, CreationDate.builderCreationDate(), null, null );


        List<Category> allCategories = List.of(inactiveCategory1, inactiveCategory2);
        when(categoryPersistencePort.findAll()).thenReturn(allCategories);

        // Act
        List<Category> actualActiveCategories = categoryUseCase.getAllCategories();

        // Assert
        assertEquals(Collections.emptyList(), actualActiveCategories);
    }

    @Test
    void getAllCategoryAttributesByCategoryIdReturnsEmptyListWhenNoAttributesExistForCategory() {
        // Arrange
        long categoryId = 1L;
        when(categoryAttributePersistencePort.findByAllByCategoryId(categoryId)).thenReturn(Collections.emptyList());

        // Act
        List<CategoryAttribute> actualAttributes = categoryUseCase.getAllCategoryAttributesByCategoryId(categoryId);

        // Assert
        assertEquals(Collections.emptyList(), actualAttributes);
    }

    @Test
    void getAllCategoryAttributesByCategoryIdReturnsListOfAttributesForGivenCategory() {
        // Arrange
        long categoryId = 1L;
        Category category = new Category(new Id(1L), "Electronics", "Electronics", true, CreationDate.builderCreationDate(), null, null );
        DefinitionAttribute definition1 = new DefinitionAttribute("Color", "TEXT");
        DefinitionAttribute definition2 = new DefinitionAttribute("Size", "NUMBER");

        CategoryAttribute attribute1 = new CategoryAttribute(category, definition1);
        CategoryAttribute attribute2 = new CategoryAttribute(category, definition2);

        List<CategoryAttribute> expectedAttributes = List.of(attribute1, attribute2);
        when(categoryAttributePersistencePort.findByAllByCategoryId(categoryId)).thenReturn(expectedAttributes);

        // Act
        List<CategoryAttribute> actualAttributes = categoryUseCase.getAllCategoryAttributesByCategoryId(categoryId);

        // Assert
        assertEquals(expectedAttributes.size(), actualAttributes.size());
        assertEquals(expectedAttributes.get(0).getCategory().getId(), actualAttributes.get(0).getCategory().getId());
        assertEquals(expectedAttributes.get(0).getDefinitionAttribute().getNameAttribute(), actualAttributes.get(0).getDefinitionAttribute().getNameAttribute());
        assertEquals(expectedAttributes.get(1).getCategory().getId(), actualAttributes.get(1).getCategory().getId());
        assertEquals(expectedAttributes.get(1).getDefinitionAttribute().getNameAttribute(), actualAttributes.get(1).getDefinitionAttribute().getNameAttribute());
    }
}