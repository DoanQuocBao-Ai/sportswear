package com.store.sportswear.Service;

import com.store.sportswear.Entity.Cart;
import com.store.sportswear.Entity.User;

public interface ICartService {
    Cart getCartByUser(User user);
    Cart saveCart(Cart cart);
}
