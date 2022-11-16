package com.store.sportswear.Service.implement;

import com.store.sportswear.Entity.Cart;
import com.store.sportswear.Entity.Category_Cart;
import com.store.sportswear.Entity.Product;
import com.store.sportswear.Repository.CategoryCartRepository;
import com.store.sportswear.Service.ICategoryCartService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryCartServiceImpl implements ICategoryCartService {
    @Autowired
    CategoryCartRepository repo;

    @Override
    public Category_Cart getByProductAndCart(Product product, Cart cart) {
        return repo.findByCartAndProduct(product,cart);
    }

    @Override
    public List<Category_Cart> getByCart(Cart cart) {
        return repo.findByCart(cart);
    }

    @Override
    public Category_Cart saveCategory_Cart(Category_Cart cc) {
        return repo.save(cc);
    }

    @Override
    public void deleteCategoryCart(Category_Cart cc) {
        repo.delete(cc);
    }

    @Override
    public void deleteAllCategoryCart(List<Category_Cart> category_cartList) {
        repo.deleteAll(category_cartList);
    }
}
