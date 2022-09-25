package at.wirecube.examples.products.application.service;

import at.wirecube.examples.products.application.entity.ProductEntity;
import at.wirecube.examples.products.application.model.Product;
import at.wirecube.examples.products.application.model.ProductSearchCriteria;
import at.wirecube.examples.products.application.model.SearchResult;
import at.wirecube.examples.products.application.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;

import static at.wirecube.examples.products.application.constants.Vat.TEN;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @BeforeEach
    public void cleanDb() {
        productRepository.deleteAll();
    }

    @Test
    void testGetProductByIdWhenProductExists() {
        Integer productId = 1;
        productRepository.save(getProductEntity());
        Product product = productService.getProductById(productId);
        assertNotNull(product);
    }

    @Test
    void testGetProductByIdWhenProduct() {
        Integer productId = 1;
        assertThrows(EntityNotFoundException.class, () -> productService.getProductById(productId));
    }

    @Test
    void testGetProducts() {

        ProductSearchCriteria criteria = ProductSearchCriteria.builder()
                .page(1)
                .size(10)
                .sortBy("id")
                .sortOrder("asc")
                .build();

        SearchResult<Product> products = productService.getAllProducts(criteria);
        assertNotNull(products);
    }

    @Test
    void testInsertProduct() {
        Product product = productService.insertProduct(getProduct());
        assertNotNull(product.getId());
    }

    @Test
    void testUpdateProduct() {
        var changedProductName = "Apple mobile";
        Product product = productService.insertProduct(getProduct());
        product.setName(changedProductName);
        product = productService.updateProduct(product);
        assertEquals(changedProductName, product.getName());
    }

    @Test
    void testDeleteProduct() {
        Product product = productService.insertProduct(getProduct());
        productService.deleteProductById(product.getId());
        assertThrows(EntityNotFoundException.class, () -> productService.getProductById(1));
    }


    private ProductEntity getProductEntity() {
        return ProductEntity.builder()
                .id(1)
                .name("Samsung mobile")
                .description("It is a nice mobile")
                .price(10000.0)
                .vat(TEN)
                .build();
    }

    private Product getProduct() {
        return Product.builder()
                .name("Samsung mobile")
                .description("It is a nice mobile")
                .price(10000.0)
                .vat(TEN)
                .build();
    }

}
