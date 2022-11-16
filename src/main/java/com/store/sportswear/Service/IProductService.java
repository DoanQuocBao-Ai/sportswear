package com.store.sportswear.Service;

import com.store.sportswear.Dto.ProductDto;
import com.store.sportswear.Dto.SearchProductDto;
import com.store.sportswear.Entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductService {
    Product updateProduct(ProductDto dto);
    Product saveProduct(ProductDto dto);
    void deleteProduct(Long id);
    Page<Product> getAllProductByFilter(SearchProductDto object, int page, int limit);
    Product getProductById(Long id);
    List<Product> getProductLatest();

}
