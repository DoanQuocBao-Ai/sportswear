package com.store.sportswear.Validator;

import com.store.sportswear.Dto.ProductDto;
import com.store.sportswear.Service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductDtoValidator implements Validator {
    @Autowired
    private ICategoryService categoryService;

    public ProductDtoValidator() {
        super();
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductDto productDto = (ProductDto) target;

        ValidationUtils.rejectIfEmpty(errors, "name", "error.name", "Tên sản phẩm không được trống");
        ValidationUtils.rejectIfEmpty(errors, "price", "error.price", "Đơn giá không được trống");
        ValidationUtils.rejectIfEmpty(errors, "sale_price", "error.sale_price", "Đơn vị giam gia không được trống");

        if(productDto.getSale_price()< 0) {
            errors.rejectValue("price", "error.price", "Đơn giá không được âm");
        }

        if(productDto.getPrice() < 0) {
            errors.rejectValue("sale_price", "error.sale_price", "Đơn giá không được âm");
        }
        String category_name = categoryService.getCategoryById((long) productDto.getCategory_id()).getCategory_name().toLowerCase();

        if(category_name.contains("sportswear".toLowerCase())) {
            ValidationUtils.rejectIfEmpty(errors, "description", "error.description", "Mo ta không được trống");
            ValidationUtils.rejectIfEmpty(errors, "featured", "error.featured", "Noi bat không được trống");
            ValidationUtils.rejectIfEmpty(errors, "best_seller", "error.best_seller", "ban chay không được trống");
        }
    }
}
