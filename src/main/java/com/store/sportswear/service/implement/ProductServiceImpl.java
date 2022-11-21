package com.store.sportswear.service.implement;

import com.querydsl.core.BooleanBuilder;
import com.store.sportswear.dto.ProductDto;
import com.store.sportswear.dto.SearchProductDto;
import com.store.sportswear.entity.Product;
import com.store.sportswear.entity.QProduct;
import com.store.sportswear.repository.BrandRepository;
import com.store.sportswear.repository.CategoryRepository;
import com.store.sportswear.repository.ProductRepository;
import com.store.sportswear.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BrandRepository brandRepository;

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
    public void deleteProduct(int id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Product> getAllProductByFilter(SearchProductDto object, int page, int limit){
        BooleanBuilder builder = new BooleanBuilder();
        String price = object.getSale_price();

        // sắp xếp theo giá
        Sort sort = Sort.by(Sort.Direction.ASC, "donGia"); // mặc định tăng dần
        if (object.getSortSalePrice().equals("desc")) { // giảm dần
            sort = Sort.by(Sort.Direction.DESC, "donGia");
        }

        if (!object.getCategoryId().equals("") && object.getCategoryId() != null) {
            builder.and(QProduct.product.category.eq(categoryRepository.findById(Integer.parseInt(object.getCategoryId())).get()));
        }

        if (!object.getBrandId().equals("") && object.getBrandId() != null) {
            builder.and(QProduct.product.brand
                    .eq(brandRepository.findById(Integer.parseInt(object.getBrandId())).get()));
        }

        // tim theo don gia
        switch (price) {
            case "duoi-2-trieu":
                builder.and(QProduct.product.sale_price.lt(2000000));
                break;

            case "2-trieu-den-4-trieu":
                builder.and(QProduct.product.sale_price.between(2000000, 4000000));
                break;

            case "4-trieu-den-6-trieu":
                builder.and(QProduct.product.sale_price.between(4000000, 6000000));
                break;

            case "6-trieu-den-10-trieu":
                builder.and(QProduct.product.sale_price.between(6000000, 10000000));
                break;

            case "tren-10-trieu":
                builder.and(QProduct.product.sale_price.gt(10000000));
                break;

            default:
                break;
        }
        return repository.findAll(builder, PageRequest.of(page, limit, sort));
    }

    @Override
    public Product getProductById(int id) {
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
        builder.and(QProduct.product.product_name.like("%" + keywords[0] + "%"));
        if (keywords.length > 1) {
            for (int i = 1; i < keywords.length; i++) {
                builder.and(QProduct.product.product_name.like("%" + keywords[i] + "%"));
            }
        }
        // Muc gia
        switch (sale_price) {
            case "duoi-2-trieu":
                builder.and(QProduct.product.sale_price.lt(2000000));
                break;

            case "2-trieu-den-4-trieu":
                builder.and(QProduct.product.sale_price.between(2000000, 4000000));
                break;

            case "4-trieu-den-6-trieu":
                builder.and(QProduct.product.sale_price.between(4000000, 6000000));
                break;

            case "6-trieu-den-10-trieu":
                builder.and(QProduct.product.sale_price.between(6000000, 10000000));
                break;

            case "tren-10-trieu":
                builder.and(QProduct.product.sale_price.gt(10000000));
                break;

            default:
                break;
        }
        return repository.findAll(builder);
    }

    @Override
    public Page<Product> getProductByName(SearchProductDto object, int page, int resultPerPage) {

        BooleanBuilder builder = new BooleanBuilder();
//		int resultPerPage = 12;
        String[] keywords = object.getKeyword();
        String sort = object.getSort();
        String price = object.getSale_price();
        String brand = object.getBrand();
        String category = object.getCategory();
        // Keyword
        builder.and(QProduct.product.product_name.like("%" + keywords[0] + "%"));
        if (keywords.length > 1) {
            for (int i = 1; i < keywords.length; i++) {
                builder.and(QProduct.product.product_name.like("%" + keywords[i] + "%"));
            }
        }
        // Muc gia
        switch (price) {
            case "duoi-2-trieu":
                builder.and(QProduct.product.sale_price.lt(2000000));
                break;

            case "2-trieu-den-4-trieu":
                builder.and(QProduct.product.sale_price.between(2000000, 4000000));
                break;

            case "4-trieu-den-6-trieu":
                builder.and(QProduct.product.sale_price.between(4000000, 6000000));
                break;

            case "6-trieu-den-10-trieu":
                builder.and(QProduct.product.sale_price.between(6000000, 10000000));
                break;

            case "tren-10-trieu":
                builder.and(QProduct.product.sale_price.gt(10000000));
                break;

            default:
                break;
        }

        // Danh muc va hang san xuat
        if (category.length()>1) {
            builder.and(QProduct.product.category.category_name.eq(category));
        }
        if (brand.length()>1) {
            builder.and(QProduct.product.brand.brand_name.eq(brand));
        }

        // Sap xep
        if (sort.equals("newest")) {
            return repository.findAll(builder, PageRequest.of(page - 1, resultPerPage, Sort.Direction.DESC, "id"));
        } else if (sort.equals("priceAsc")) {
            return repository.findAll(builder, PageRequest.of(page - 1, resultPerPage, Sort.Direction.ASC, "donGia"));
        } else if (sort.equals("priceDes")) {
            return repository.findAll(builder, PageRequest.of(page - 1, resultPerPage, Sort.Direction.DESC, "donGia"));
        }
        return repository.findAll(builder, PageRequest.of(page - 1, resultPerPage));
    }

    @Override
    public List<Product> getAllProductByList(Set<Integer> idList) {
        return repository.findByIdIn(idList);
    }

    @Override
    public Page<Product> getAllProductByNameForAdmin(String nameProduct, int page, int size) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QProduct.product.product_name.like("%" + nameProduct + "%"));
        return repository.findAll(builder, PageRequest.of(page, size));
    }

    @Override
    public Iterable<Product> getProductByCategory(String category) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QProduct.product.category.category_name.eq(category));
        return repository.findAll(builder);
    }

    @Override
    public Page<Product> getProductByBrand(SearchProductDto object, int page, int resultPerPage) {
        BooleanBuilder builder = new BooleanBuilder();
        String price = object.getPrice();
        String brand = object.getBrand();
        String category = object.getCategory();

        // Muc gia
        switch (price) {
            case "duoi-2-trieu":
                builder.and(QProduct.product.sale_price.lt(2000000));
                break;

            case "2-trieu-den-4-trieu":
                builder.and(QProduct.product.sale_price.between(2000000, 4000000));
                break;

            case "4-trieu-den-6-trieu":
                builder.and(QProduct.product.sale_price.between(4000000, 6000000));
                break;

            case "6-trieu-den-10-trieu":
                builder.and(QProduct.product.sale_price.between(6000000, 10000000));
                break;

            case "tren-10-trieu":
                builder.and(QProduct.product.sale_price.gt(10000000));
                break;

            default:
                break;
        }

        if (brand.length()>1) {
            builder.and(QProduct.product.category.category_name.eq(category));
        }
        if (brand.length()>1) {
            builder.and(QProduct.product.brand.brand_name.eq(brand));
        }

        return repository.findAll(builder, PageRequest.of(page - 1, resultPerPage));

    }
}
