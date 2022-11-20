package com.store.sportswear.Service;

import com.store.sportswear.Entity.Brand;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IBrandService {
    List<Brand>getAllBrand();
    Page<Brand> getAllBrand(int page, int size);
    Brand getBrandById(long id);
    Brand saveBrand(Brand brand);
    Brand updateBrand(Brand brand);
    void deleteBrandById(long id);
}
