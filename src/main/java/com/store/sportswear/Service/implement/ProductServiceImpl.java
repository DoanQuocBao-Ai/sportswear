package com.store.sportswear.Service.implement;

import com.querydsl.core.BooleanBuilder;
import com.store.sportswear.Dto.ProductDto;
import com.store.sportswear.Dto.SearchProductDto;
import com.store.sportswear.Entity.Product;
import com.store.sportswear.Repository.BrandRepository;
import com.store.sportswear.Repository.CategoryRepository;
import com.store.sportswear.Repository.ProductRepository;
import com.store.sportswear.Service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    ProductRepository repository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    BrandRepository brandRepository;

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
        return repository.findFirstByIdDesc("Quan Ao");
    }

    @Override
    public Iterable<Product> getProductByNameWithPaginate(SearchProductDto dto) {
        BooleanBuilder builder=new BooleanBuilder();
        int resultPerPage=12;
        String[] keywords= dto.getKeyword();
        String sort= dto.getSort();
        String sale_price= dto.getSale_price();
        return null;
    }

    @Override
    public Page<Product> getProductByName(SearchProductDto dto, int page, int resultPerPage) {
        return null;
    }

    @Override
    public List<Product> getAllProductByList(Set<Long> idList) {
        return null;
    }

    @Override
    public Page<Product> getAllProductByNameForAdmin(String nameProduct, int page, int size) {
        return null;
    }

    @Override
    public Iterable<Product> getProductByCategory(String category) {
        return null;
    }

    @Override
    public Page<Product> getProductByBrand(SearchProductDto dto, int page, int resultPerPage) {
        return null;
    }
}
