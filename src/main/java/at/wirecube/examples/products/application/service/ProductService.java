package at.wirecube.examples.products.application.service;

import at.wirecube.examples.products.application.model.Product;
import at.wirecube.examples.products.application.model.ProductSearchCriteria;
import at.wirecube.examples.products.application.model.SearchResult;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;

@Validated
public interface ProductService {

    Product insertProduct(@NotNull Product product);

    Product updateProduct(@NotNull Product product);

    void deleteProductById(@NotNull Integer id);

    Product getProductById(@NotNull Integer id);

    SearchResult<Product> getAllProducts(@NotNull ProductSearchCriteria criteria);
}
