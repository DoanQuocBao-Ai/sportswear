package com.store.sportswear.service.implement;

import com.store.sportswear.entity.Cart;
import com.store.sportswear.entity.UserSystem;
import com.store.sportswear.repository.CartRepository;
import com.store.sportswear.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private CartRepository repo;

    public CartServiceImpl() {
        super();
    }

    @Override
    public Cart getCartByUser(UserSystem userSystem) {
        return repo.findByUser(userSystem);
    }

    @Override
    public Cart saveCart(Cart cart) {
        return repo.save(cart);
    }
}
