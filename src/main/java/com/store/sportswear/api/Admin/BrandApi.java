package com.store.sportswear.api.Admin;

import com.store.sportswear.entity.Brand;
import com.store.sportswear.entity.ResponseObject;
import com.store.sportswear.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/nhan-hieu")
public class BrandApi {
    @Autowired
    private IBrandService brandService;

    public BrandApi() {
        super();
    }

    @GetMapping("/all")
    public Page<Brand> getAllBrand(@RequestParam(defaultValue = "1") int page) {
        return brandService.getAllBrand(page-1,6);
    }

    @GetMapping("/{id}")
    public Brand getBrandById(@PathVariable int id) {
        return brandService.getBrandById(id);
    }

    @PostMapping(value = "/save")
    public ResponseObject addBrand(@RequestBody @Valid Brand newBrand, BindingResult result) {

        ResponseObject responseObject = new ResponseObject();

        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            responseObject.setErrorMessages(errors);
            responseObject.setStatus("fail");
        } else {
            brandService.saveBrand(newBrand);
            responseObject.setObject(newBrand);
            responseObject.setStatus("success");
        }
        return responseObject;
    }

    @PutMapping(value = "/update")
    public ResponseObject updateBrand(@RequestBody @Valid Brand editBrand, BindingResult result) {

        ResponseObject responseObject = new ResponseObject();
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            responseObject.setErrorMessages(errors);
            responseObject.setStatus("fail");
            errors = null;
        } else {
            brandService.updateBrand(editBrand);
            responseObject.setObject(editBrand);
            responseObject.setStatus("success");
        }

        return responseObject;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBrand(@PathVariable int id) {
        brandService.deleteBrandById(id);
        return "OK !";
    }
}
