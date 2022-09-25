package at.wirecube.examples.products.application.service;

import at.wirecube.examples.products.application.model.Product;

import java.util.List;

public interface ProductService {

    Product insertProduct(Product product);

    Product updateProduct(Product product);

    void deleteProductById(Integer id);

    Product getProductById(Integer id);

    List<Product> getAllProducts();
}
