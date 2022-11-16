package com.store.sportswear.Service;

import com.store.sportswear.Entity.Cart;
import com.store.sportswear.Entity.Category_Cart;
import com.store.sportswear.Entity.Product;

import java.util.List;

public interface ICategoryCartService {
    Category_Cart getByProductAndCart(Product product, Cart cart);
    List<Category_Cart> getByCart(Cart cart);
    Category_Cart saveCategory_Cart(Category_Cart cc);
    void deleteCategoryCart(Category_Cart cc);
    void deleteAllCategoryCart(List<Category_Cart> category_cartList);
}
