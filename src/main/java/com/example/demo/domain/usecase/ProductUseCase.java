package com.example.demo.domain.usecase;

import com.example.demo.adapters.driving.http.handler.commands.ProductAttributeCommandRequest;
import com.example.demo.domain.api.IProductServicePort;
import com.example.demo.domain.exceptions.CategoryNotFoundException;
import com.example.demo.domain.exceptions.DefinitionAttributeNotFound;
import com.example.demo.domain.exceptions.DefinitionAttributeNotFoundForIdException;
import com.example.demo.domain.exceptions.ProductBelongsOtherUserException;
import com.example.demo.domain.exceptions.ProductNotFoundException;
import com.example.demo.domain.model.Category;
import com.example.demo.domain.model.CustomPage;
import com.example.demo.domain.model.Product;
import com.example.demo.domain.model.value.object.DefinitionAttribute;
import com.example.demo.domain.model.value.object.Inventory;
import com.example.demo.domain.model.value.object.ProductAttribute;
import com.example.demo.domain.spi.ICategoryPersistencePort;
import com.example.demo.domain.spi.IDefinitionAttributePersistencePort;
import com.example.demo.domain.spi.IProductAttributesPersistencePort;
import com.example.demo.domain.spi.IProductPersistencePort;
import com.example.demo.domain.usecase.utils.UseCaseUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.domain.model.value.object.CreationDate.builderCreationDate;
import static com.example.demo.domain.model.value.object.DeletionDate.builderDeletionDate;

public class ProductUseCase implements IProductServicePort {
    private final IProductPersistencePort productPersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;
    private final IDefinitionAttributePersistencePort definitionAttributePersistencePort;
    private final IProductAttributesPersistencePort productAttributesPersistencePort;
    private final UseCaseUtils useCaseUtils;

    public ProductUseCase(IProductPersistencePort productPersistencePort,
                          ICategoryPersistencePort categoryPersistencePort,
                          IDefinitionAttributePersistencePort definitionAttributePersistencePort,
                          IProductAttributesPersistencePort productAttributesPersistencePort,
                          UseCaseUtils useCaseUtils) {
        this.productPersistencePort = productPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
        this.definitionAttributePersistencePort = definitionAttributePersistencePort;
        this.productAttributesPersistencePort = productAttributesPersistencePort;
        this.useCaseUtils = useCaseUtils;
    }

    // Tenemos problemas para actualizar. Esta creando otro objeto, posiblemente por las anotaciones de Cascade.
    // Intentar con FetchType.EAGER o con query update.

    // https://www.baeldung.com/spring-data-jpa-getreferencebyid-findbyid-methods
    // https://stackoverflow.com/questions/39741102/how-to-beautifully-update-a-jpa-entity-in-spring-data

    @Override
    public CustomPage<Product> getProductFeed() {
        //TODO: Poner regla de negocio que solamente muestre los productos cuyo Inventory sea TRUE. y cuyo Amount sea mayor a 0.
        return productPersistencePort.getProductFeed();
    }

    @Override
    public void createProduct(Product product, long categoryId, List<ProductAttributeCommandRequest> attributes) {


        Category category = getCategoryById(categoryId);
        product.setCategory(category);
        product.setCreationDate(builderCreationDate());
        product.setUserProviderId(useCaseUtils.getUserIdFromAuthenticationContextService());

        List<ProductAttribute> productAttributes = attributes.stream()
                .map(attribute -> {
                    DefinitionAttribute definitionAttribute = getDefinitionAttributeById(attribute.getDefinitionAttributeId());
                    ProductAttribute productAttribute = new ProductAttribute(
                            null,
                            definitionAttribute,
                            attribute.getValue()
                    );
                    return productAttribute;
                }).collect(Collectors.toList());


        product.setAttributes(productAttributes);
        saveProduct(product);

    }

    @Override
    public void disableProduct(Long productId) {
        Product product = getProductById(productId);

        useCaseUtils.validationProductBelongsToUserProviderId(product.getUserProviderId(), productId);
        product.setDeletionDate(builderDeletionDate(product.getCreationDate()));
        product.setInventory(new Inventory(product.getInventory().getQuantity(), false));

        System.out.println("from use case: " + product.getInventory());
        System.out.println("from isAcrtive:  " + product.getInventory().getIsActive());
        productPersistencePort.saveProduct(product);
    }

    @Override
    public CustomPage<Product> getAllStockByProviderId() {
        int size = 100;
        int page = 0;
        return productPersistencePort.getAllProductsByProviderId(useCaseUtils.getUserIdFromAuthenticationContextService(), page, size);
    }

    @Override
    public void addProductQuantity(Long productId, Integer quantity) {
        Product product = getProductById(productId);
        System.out.println("product = " + product.getCreationDate().getValue());
        useCaseUtils.validationProductBelongsToUserProviderId(product.getUserProviderId(), productId);
        product.setInventory(new Inventory(product.getInventory().getQuantity() + quantity, true));
        productPersistencePort.saveProduct(product);
    }

    @Override
    public Product getProductByIdAndValidationUserProviderId(Long productId) {
        Product product = getProductById(productId);
        useCaseUtils.validationProductBelongsToUserProviderId(product.getUserProviderId(), productId);
        return product;
    }



    private Product getProductById(long productId){
        return productPersistencePort.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
    }
    private Category getCategoryById(long categoryId) {
        return categoryPersistencePort.findById(categoryId)
                .orElseThrow(CategoryNotFoundException::new);
    }

    private DefinitionAttribute getDefinitionAttributeById(long definitionAttributeId) {
        return definitionAttributePersistencePort.findById(definitionAttributeId)
                .orElseThrow(DefinitionAttributeNotFound::new);
    }

    private List<DefinitionAttribute> getListDefinitionAttributeById(List<ProductAttributeCommandRequest> attributes) {
        return attributes.stream()
                .map(attribute -> getDefinitionAttributeById(attribute.getDefinitionAttributeId()))
                .toList();
    }

    private void saveProduct(Product product){
        productPersistencePort.saveProduct(product);
    }
}
