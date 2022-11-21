package com.store.sportswear.service;

import com.store.sportswear.entity.Brand;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IBrandService {
    List<Brand>getAllBrand();
    Page<Brand> getAllBrand(int page, int size);
    Brand getBrandById(int id);
    Brand saveBrand(Brand brand);
    Brand updateBrand(Brand brand);
    void deleteBrandById(int id);
}
