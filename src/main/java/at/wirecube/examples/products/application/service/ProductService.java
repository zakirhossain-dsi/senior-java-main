package at.wirecube.examples.products.application.service;

import at.wirecube.examples.products.application.model.Product;
import at.wirecube.examples.products.application.model.ProductSearchCriteria;
import at.wirecube.examples.products.application.model.SearchResult;

public interface ProductService {

    Product insertProduct(Product product);

    Product updateProduct(Product product);

    void deleteProductById(Integer id);

    Product getProductById(Integer id);

    SearchResult<Product> getAllProducts(ProductSearchCriteria criteria);
}
