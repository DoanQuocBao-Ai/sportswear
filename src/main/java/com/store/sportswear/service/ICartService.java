package com.store.sportswear.service;

import com.store.sportswear.entity.Cart;
import com.store.sportswear.entity.UserSystem;

public interface ICartService {
    Cart getCartByUser(UserSystem userSystem);
    Cart saveCart(Cart cart);
}
