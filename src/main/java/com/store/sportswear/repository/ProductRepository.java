package com.store.sportswear.repository;

import com.store.sportswear.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>, QuerydslPredicateExecutor<Product> {
    List<Product> findFirstByIdDesc(String dm);
    List<Product> findByIdIn(Set<Integer>idList);
}
