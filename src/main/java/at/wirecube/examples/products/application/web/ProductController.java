package at.wirecube.examples.products.application.web;

import at.wirecube.examples.products.application.model.Product;
import at.wirecube.examples.products.application.service.ProductService;
import at.wirecube.examples.products.application.validation.OnCreate;
import at.wirecube.examples.products.application.validation.OnUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static at.wirecube.examples.products.application.constants.ProductApiUrl.BASE;
import static at.wirecube.examples.products.application.constants.ProductApiUrl.PATH_ID;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
@ResponseStatus(HttpStatus.OK)
@RequestMapping(BASE)
@Tag(name = "Product Controller",
        description = "This controller exposes interfaces to interact with product.")
public class ProductController {

    private final ProductService productService;

    @Operation(description = "Creates a product")
    @Validated(OnCreate.class)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Product insertProduct(@RequestBody @Valid Product product) {
        log.info("Inserting a product.");
        return productService.insertProduct(product);
    }

    @Operation(description = "Updates a product by id")
    @Validated(OnUpdate.class)
    @PutMapping(value = PATH_ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Product updateProductById(@PathVariable Integer id, @RequestBody @Valid Product product) {

        if (!Objects.equals(id, product.getId())) {
            throw new IllegalArgumentException(
                    "Product id on the path variable and request body should be the same.");
        }

        log.info("Updating a product with productId: {}", id);
        return productService.updateProduct(product);
    }

    @Operation(description = "Deletes a product by id")
    @DeleteMapping(value = PATH_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> deleteProductById(@PathVariable Integer id) {
        log.info("Deleting a product with id: {}", id);
        productService.deleteProductById(id);
        return Map.of("message", String.format("Product with id=%s has been deleted successfully.", id));
    }

    @Operation(description = "Fetches a product by id")
    @GetMapping(value = PATH_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProductById(@PathVariable Integer id) {
        log.info("Fetching a product with id: {}", id);
        return productService.getProductById(id);
    }

    @Operation(description = "Fetches all products")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAllProducts() {
        log.info("Fetching all products");
        return productService.getAllProducts();
    }
}
