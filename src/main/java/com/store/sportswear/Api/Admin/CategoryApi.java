package com.store.sportswear.Api.Admin;

import com.store.sportswear.Entity.Categories;
import com.store.sportswear.Entity.ResponseObject;
import com.store.sportswear.Service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/danh-muc")
public class CategoryApi {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/all")
    public Page<Categories> getAllDanhMuc(@RequestParam(defaultValue = "1") int page) {
        return categoryService.getAllCategoryForPageable(page-1,6);
    }

    @GetMapping("/allForReal")
    public List<Categories> getRealAllDanhMuc() {
        return categoryService.getAllCategory();
    }

    @GetMapping("/{id}")
    public Categories getDanhMucById(@PathVariable long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping(value = "/save")
    public ResponseObject addDanhMuc(@RequestBody @Valid Categories newDanhMuc, BindingResult result, HttpServletRequest request) {

        ResponseObject responseObject = new ResponseObject();

        if (result.hasErrors()) {

            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            responseObject.setErrorMessages(errors);

            List<String> keys = new ArrayList<String>(errors.keySet());
            for (String key: keys) {
                System.out.println(key + ": " + errors.get(key));
            }

            responseObject.setStatus("fail");
            errors = null;
            ;
        } else {
            categoryService.saveCategory(newDanhMuc);
            responseObject.setObject(newDanhMuc);
            responseObject.setStatus("success");
        }
        return responseObject;
    }

    @PutMapping(value = "/update")
    public ResponseObject updateCategory(@RequestBody @Valid Categories editCategory, BindingResult result, HttpServletRequest request) {

        ResponseObject ro = new ResponseObject();
        if (result.hasErrors()) {

            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            ro.setErrorMessages(errors);
            ro.setStatus("fail");
            errors = null;

        } else {
            categoryService.updateCategory(editCategory);
            ro.setObject(editCategory);
            ro.setStatus("success");
        }

        return ro;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable long id, HttpServletRequest request) {
        categoryService.deleteCategory(id);
        request.getSession().setAttribute("listDanhMuc", categoryService.getAllCategory());
        return "OK !";
    }
}
