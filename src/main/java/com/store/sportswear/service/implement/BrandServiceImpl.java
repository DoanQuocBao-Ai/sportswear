package com.store.sportswear.service.implement;

import com.store.sportswear.entity.Brand;
import com.store.sportswear.repository.BrandRepository;
import com.store.sportswear.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements IBrandService {
    @Autowired
    private BrandRepository repo;

    public BrandServiceImpl() {
        super();
    }

    @Override
    public Brand getBrandById(int id)
    {
        return repo.findById(id).get();
    }
    @Override
    public Brand saveBrand(Brand brand)
    {
        return repo.save(brand);
    }
    @Override
    public Brand updateBrand(Brand brand)
    {
        return repo.save(brand);
    }
    @Override
    public void deleteBrandById(int id)
    {
        repo.deleteById(id);
    }
    @Override
    public Page<Brand> getAllBrand(int page, int size) {
        return repo.findAll(PageRequest.of(page,size));
    }
    @Override
    public List<Brand> getAllBrand()
    {
        return repo.findAll();
    }
}
