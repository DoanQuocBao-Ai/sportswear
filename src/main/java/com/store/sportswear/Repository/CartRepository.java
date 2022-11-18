package com.store.sportswear.Repository;

import com.store.sportswear.Entity.Cart;
import com.store.sportswear.Entity.UserSystem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByUser(UserSystem n);
}
