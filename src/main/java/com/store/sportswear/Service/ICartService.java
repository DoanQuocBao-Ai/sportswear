package com.store.sportswear.Service;

import com.store.sportswear.Entity.Cart;
import com.store.sportswear.Entity.UserSystem;

public interface ICartService {
    Cart getCartByUser(UserSystem userSystem);
    Cart saveCart(Cart cart);
}
