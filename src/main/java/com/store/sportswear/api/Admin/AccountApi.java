package com.store.sportswear.api.Admin;

import com.store.sportswear.dto.AccountDto;
import com.store.sportswear.entity.ResponseObject;
import com.store.sportswear.entity.Role;
import com.store.sportswear.entity.UserSystem;
import com.store.sportswear.service.IRoleService;
import com.store.sportswear.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/account")
public class AccountApi {
    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    public AccountApi() {
        super();
    }

    @GetMapping("/all")
    public Page<UserSystem> getUserByRole(@RequestParam("name") String name,
                                          @RequestParam(defaultValue = "1") int page) {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleService.getRoleByName(name));
        return userService.getUserByRole(roleSet, page);
    }

    @PostMapping("/save")
    public ResponseObject saveAccount(@RequestBody @Valid AccountDto dto, BindingResult result, Model model) {

        ResponseObject responseObject = new ResponseObject();
        if(userService.getUserByEmail(dto.getEmail()) != null) {
            result.rejectValue("email", "error.email","Email đã được đăng ký");
        }
        if(!dto.getConfirmPassword().equals(dto.getPassword())) {
            result.rejectValue("confirmPassword", "error.confirmPassword","Nhắc lại mật khẩu không đúng");
        }

        if (result.hasErrors()) {
            setErrorsForResponseObject(result, responseObject);
        } else {
            responseObject.setStatus("success");
            userService.saveUserForAdmin(dto);
        }
        return responseObject;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAccount(@PathVariable int id) {
        userService.deleteUserById(id);
    }
    public void setErrorsForResponseObject(BindingResult result, ResponseObject object) {

        Map<String, String> error= result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        object.setErrorMessages(error);
        object.setStatus("fail");

        List<String> keys = new ArrayList<String>(error.keySet());
        for (String key: keys) {
            System.out.println(key + ": " + error.get(key));
        }

        error = null;
    }
}
