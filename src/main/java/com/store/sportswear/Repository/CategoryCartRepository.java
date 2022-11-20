package com.store.sportswear.Repository;

import com.store.sportswear.Entity.Cart;
import com.store.sportswear.Entity.Category_Cart;
import com.store.sportswear.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryCartRepository extends JpaRepository<Category_Cart,Long> {
    Category_Cart findByCartAndProduct(Product product, Cart cart);
    List<Category_Cart> findByCart(Cart cart);

}
