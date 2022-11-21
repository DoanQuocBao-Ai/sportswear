package com.store.sportswear.api.Admin;

import com.store.sportswear.entity.Categories;
import com.store.sportswear.entity.ResponseObject;
import com.store.sportswear.service.ICategoryService;
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
@RequestMapping("api/category")
public class CategoryApi {
    @Autowired
    private ICategoryService categoryService;

    public CategoryApi() {
        super();
    }

    @GetMapping("/all")
    public Page<Categories> getAllCategory(@RequestParam(defaultValue = "1") int page) {
        return categoryService.getAllCategoryForPageable(page-1,6);
    }

    @GetMapping("/allForReal")
    public List<Categories> getRealAllCategory() {
        return categoryService.getAllCategory();
    }

    @GetMapping("/{id}")
    public Categories getCategoryById(@PathVariable int id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping(value = "/save")
    public ResponseObject addCategory(@RequestBody @Valid Categories newCategory, BindingResult result, HttpServletRequest request) {

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
            categoryService.saveCategory(newCategory);
            responseObject.setObject(newCategory);
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
    public String deleteCategory(@PathVariable int id, HttpServletRequest request) {
        categoryService.deleteCategory(id);
        request.getSession().setAttribute("listCategory", categoryService.getAllCategory());
        return "OK !";
    }
}
