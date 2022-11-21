package com.store.sportswear.service.implement;

import com.store.sportswear.entity.Cart;
import com.store.sportswear.entity.Category_Cart;
import com.store.sportswear.entity.Product;
import com.store.sportswear.repository.CategoryCartRepository;
import com.store.sportswear.service.ICategoryCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryCartServiceImpl implements ICategoryCartService {
    @Autowired
    private CategoryCartRepository repo;

    public CategoryCartServiceImpl() {
        super();
    }

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
