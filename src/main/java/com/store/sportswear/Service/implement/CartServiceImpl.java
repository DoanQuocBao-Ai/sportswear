package com.store.sportswear.Service.implement;

import com.store.sportswear.Entity.Cart;
import com.store.sportswear.Entity.UserSystem;
import com.store.sportswear.Repository.CartRepository;
import com.store.sportswear.Service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;

public class CartServiceImpl implements ICartService {
    @Autowired
    private CartRepository repo;

    @Override
    public Cart getCartByUser(UserSystem userSystem) {
        return repo.findByUser(userSystem);
    }

    @Override
    public Cart saveCart(Cart cart) {
        return repo.save(cart);
    }
}
