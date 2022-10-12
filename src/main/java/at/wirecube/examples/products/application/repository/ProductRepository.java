package at.wirecube.examples.products.application.repository;

import at.wirecube.examples.products.application.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer>, QuerydslPredicateExecutor<ProductEntity> {


}
