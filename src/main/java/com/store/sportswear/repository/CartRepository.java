package com.store.sportswear.repository;

import com.store.sportswear.entity.Cart;
import com.store.sportswear.entity.UserSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    Cart findByUser(UserSystem n);
}
