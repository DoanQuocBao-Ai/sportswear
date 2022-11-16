package com.store.sportswear.Service.implement;

import com.store.sportswear.Entity.Cart;
import com.store.sportswear.Entity.User;
import com.store.sportswear.Repository.CartRepository;
import com.store.sportswear.Service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;

public class CartServiceImpl implements ICartService {
    @Autowired
    CartRepository repo;

    @Override
    public Cart getCartByUser(User user) {
        return repo.findByUser(user);
    }

    @Override
    public Cart saveCart(Cart cart) {
        return repo.save(cart);
    }
}
