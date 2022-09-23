package at.wirecube.examples.products.application.service;

import at.wirecube.examples.products.application.entity.ProductEntity;
import at.wirecube.examples.products.application.model.Product;
import at.wirecube.examples.products.application.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findById(id)
                .map(productEntity -> modelMapper.map(productEntity, Product.class))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Product was not found for id=%s", id)));
    }

    @Override
    public List<Product> getAllProducts() {
        Iterable<ProductEntity> productEntities = productRepository.findAll();
        return StreamSupport.stream(productEntities.spliterator(), false)
                .map(productEntity -> modelMapper.map(productEntity, Product.class))
                .collect(Collectors.toList());
    }

    @Override
    public Product insertProduct(Product product) {
        ProductEntity productEntity = productRepository.save(modelMapper.map(product, ProductEntity.class));
        product.setId(productEntity.getId());
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        getProductById(product.getId());
        productRepository.save(modelMapper.map(product, ProductEntity.class));
        return product;
    }

    @Override
    public void deleteProductById(Integer id) {
        getProductById(id);
        productRepository.deleteById(id);
    }

}
