package com.store.sportswear.Service;

import com.store.sportswear.Entity.Categories;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICategoryService {
    Page<Categories> getAllCategoryForPageable(int page, int size);
    List<Categories> getAllCategory();
    Categories saveCategory(Categories categories);
    Categories updateCategory(Categories categories);
    void deleteCategory(long id);
    Categories getCategoryById(long id);
}
