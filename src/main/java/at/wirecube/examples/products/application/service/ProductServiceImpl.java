package at.wirecube.examples.products.application.service;

import at.wirecube.examples.products.application.entity.ProductEntity;
import at.wirecube.examples.products.application.entity.QProductEntity;
import at.wirecube.examples.products.application.model.Product;
import at.wirecube.examples.products.application.model.ProductSearchCriteria;
import at.wirecube.examples.products.application.model.SearchResult;
import at.wirecube.examples.products.application.repository.ProductRepository;
import at.wirecube.examples.products.application.utils.AppUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final JPAQueryFactory queryFactory;

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findById(id)
                .map(productEntity -> modelMapper.map(productEntity, Product.class))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Product was not found for id=%s", id)));
    }

    @Override
    public SearchResult<Product> getAllProducts(ProductSearchCriteria criteria) {

        QProductEntity queryProduct = QProductEntity.productEntity;

        BooleanBuilder queryBuilder = new BooleanBuilder(queryProduct.id.isNotNull());

        if(Objects.nonNull(criteria.getPrice())){
            queryBuilder.and(queryProduct.price.lt(criteria.getPrice()));
        }

        if(Objects.nonNull(criteria.getDescriptionLength())){
            queryBuilder.and(queryProduct.description.length().lt(criteria.getDescriptionLength()));
        }

        if(Objects.nonNull(criteria.getVat())){
            queryBuilder.and(queryProduct.vat.stringValue().equalsIgnoreCase(criteria.getVat()));
        }

        Page<ProductEntity> result = productRepository.findAll(queryBuilder.getValue(), AppUtils.getPageable(criteria));

        List<Product> products = result.stream()
                .map(productEntity -> modelMapper.map(productEntity, Product.class))
                .collect(Collectors.toList());

        return SearchResult.<Product>builder()
                .totalElements(result.getTotalElements())
                .currentElements(products.size())
                .totalPages(result.getTotalPages())
                .currentPage(criteria.getPage())
                .data(products)
                .build();
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
