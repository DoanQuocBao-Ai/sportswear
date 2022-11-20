package com.store.sportswear.Service.implement;

import com.store.sportswear.Entity.Categories;
import com.store.sportswear.Repository.CategoryRepository;
import com.store.sportswear.Service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    CategoryRepository repo;

    public CategoryServiceImpl() {
        super();
    }

    @Override
    public Page<Categories> getAllCategoryForPageable(int page, int size) {
        return repo.findAll(PageRequest.of(page,size));
    }

    @Override
    public List<Categories> getAllCategory() {
        return repo.findAll();
    }

    @Override
    public Categories saveCategory(Categories categories) {
        return repo.save(categories);
    }

    @Override
    public Categories updateCategory(Categories categories) {
        return repo.save(categories);
    }

    @Override
    public void deleteCategory(long id) {
        repo.deleteById(id);
    }

    @Override
    public Categories getCategoryById(long id) {
        return repo.findById(id).get();
    }
}
