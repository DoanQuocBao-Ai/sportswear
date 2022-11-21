package com.store.sportswear.service.implement;

import com.store.sportswear.entity.Categories;
import com.store.sportswear.repository.CategoryRepository;
import com.store.sportswear.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryRepository repo;

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
    public void deleteCategory(int id) {
        repo.deleteById(id);
    }

    @Override
    public Categories getCategoryById(int id) {
        return repo.findById(id).get();
    }
}
