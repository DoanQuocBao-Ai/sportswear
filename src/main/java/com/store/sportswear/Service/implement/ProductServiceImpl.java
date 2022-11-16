package com.store.sportswear.Service.implement;

import com.store.sportswear.Dto.ProductDto;
import com.store.sportswear.Dto.SearchProductDto;
import com.store.sportswear.Entity.Product;
import com.store.sportswear.Repository.ProductRepository;
import com.store.sportswear.Service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    ProductRepository repository;


    public ProductServiceImpl() {
        super();
    }
    public Product convertFromProduct(ProductDto dto)
    {
        Product product=new Product();
        if(!dto.getId().equals(""))
        {
            product.setId(Integer.parseInt(dto.getId()));
        }
        return product;
    }
    @Override
    public Product updateProduct(ProductDto dto) {
        return repository.save(convertFromProduct(dto));
    }

    @Override
    public Product saveProduct(ProductDto dto) {
        return repository.save(convertFromProduct(dto));
    }

    @Override
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Product> getAllProductByFilter(SearchProductDto object, int page, int limit) {
        return null;
    }

    @Override
    public Product getProductById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Product> getProductLatest() {
        return null;
    }
}
