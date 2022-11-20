package com.store.sportswear.Api.Admin;

import com.store.sportswear.Dto.ProductDto;
import com.store.sportswear.Dto.SearchProductDto;
import com.store.sportswear.Entity.Product;
import com.store.sportswear.Entity.ResponseObject;
import com.store.sportswear.Service.IProductService;
import com.store.sportswear.Validator.ProductDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/product")
public class ProductApi {
    @Autowired
    private ProductDtoValidator validator;

    @Autowired
    private IProductService productService;

    @InitBinder("productDto")
    protected void initialiseBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @GetMapping("/all")
    public Page<Product> getAllProductByFilter(@RequestParam(defaultValue = "1") int page,
                                               @RequestParam String category_id, @RequestParam String brand_id, @RequestParam String price, @RequestParam String sortPrice) {
        SearchProductDto searchObject = new SearchProductDto();
        searchObject.setCategoryId(category_id);
        searchObject.setBrandId(brand_id);
        searchObject.setPrice(price);
        searchObject.setSortSalePrice(sortPrice);

        Page<Product> list = productService.getAllProductByFilter(searchObject, page-1, 10);
        return list;
    }

    @GetMapping("/latest")
    public List<Product> getLatestProduct(){
        return productService.getProductLatest();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable long id) {
        return productService.getProductById(id);
    }


    @GetMapping("/")
    public Page<Product> getProductById(@RequestParam String name, @RequestParam(defaultValue = "1") int page) {
        return productService.getAllProductByNameForAdmin(name, page-1, 10 );
    }

    @PostMapping(value = "/save")
    public ResponseObject addProduct(@ModelAttribute @Valid ProductDto newProductDto, BindingResult result,
                                     HttpServletRequest request) {

        ResponseObject responseObject = new ResponseObject();

        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            errors.forEach((k, v) -> System.out.println(" test: Key : " + k + " Value : " + v));
            responseObject.setErrorMessages(errors);
            responseObject.setStatus("fail");
            errors = null;
        } else {
            Product product = productService.saveProduct(newProductDto);
            responseObject.setObject(product);
            saveImageForProduct(product, newProductDto, request);
            responseObject.setStatus("success");
        }
        return responseObject;
    }


    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return "OK !";
    }


    public void saveImageForProduct(Product product, ProductDto productDto, HttpServletRequest request) {

        MultipartFile productImage = productDto.getImage();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        Path path = Paths.get(rootDirectory + "/resources/images/" + product.getId() + ".png");
        System.out.println(productImage != null && !productImage.isEmpty());
        if (productImage != null && !productImage.isEmpty()) {

            try {
                productImage.transferTo(new File(path.toString()));
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException("Product image saving failed", ex);
            }
        }
    }
}
