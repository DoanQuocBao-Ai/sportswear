package com.store.sportswear.Repository;

import com.store.sportswear.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product,Long>, QuerydslPredicateExecutor<Product> {
    List<Product> findFirstByIdDesc(String dm);
    List<Product> findByIdIn(Set<Long>idList);
}
