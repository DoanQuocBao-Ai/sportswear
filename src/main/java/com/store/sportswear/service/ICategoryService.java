package com.store.sportswear.service;

import com.store.sportswear.entity.Categories;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICategoryService {
    Page<Categories> getAllCategoryForPageable(int page, int size);
    List<Categories> getAllCategory();
    Categories saveCategory(Categories categories);
    Categories updateCategory(Categories categories);
    void deleteCategory(int id);
    Categories getCategoryById(int id);
}
