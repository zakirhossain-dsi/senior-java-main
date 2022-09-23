package at.wirecube.examples.products.application.repository;

import at.wirecube.examples.products.application.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {


}
