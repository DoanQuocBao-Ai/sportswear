package com.store.sportswear.Service.implement;

import com.store.sportswear.Entity.Brand;
import com.store.sportswear.Repository.BrandRepository;
import com.store.sportswear.Service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements IBrandService {
    @Autowired
    private BrandRepository repo;
    @Override
    public Brand getBrandById(long id)
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
    public void deleteBrandById(Long id)
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
