package com.store.sportswear.repository;

import com.store.sportswear.entity.Cart;
import com.store.sportswear.entity.Category_Cart;
import com.store.sportswear.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryCartRepository extends JpaRepository<Category_Cart,Integer> {
    Category_Cart findByCartAndProduct(Product product, Cart cart);
    List<Category_Cart> findByCart(Cart cart);

}
