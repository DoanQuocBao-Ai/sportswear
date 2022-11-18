package com.store.sportswear.Api.Admin;

import com.store.sportswear.Dto.ChangePasswordDto;
import com.store.sportswear.Entity.ResponseObject;
import com.store.sportswear.Entity.UserSystem;
import com.store.sportswear.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/profile")
public class ProfileApi {
    @Autowired
    private IUserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/{id}")
    public UserSystem getUserById(@PathVariable long id) {
        UserSystem userSystem = userService.getUserById(id);
        return userSystem;
    }

    @PostMapping("/changePassword")
    public ResponseObject changePass(@RequestBody @Valid ChangePasswordDto dto, BindingResult result,
                                     HttpServletRequest request) {
        System.out.println(dto.toString());
        UserSystem currentUserSystem = getSessionUser(request);
        ResponseObject responseObject = new ResponseObject();
        if (!passwordEncoder.matches( dto.getOldPassword(), currentUserSystem.getUser_password())) {
            result.rejectValue("oldPassword", "error.oldPassword", "Mật khẩu cũ không đúng");
        }

        if (!dto.getNewPassword().equals(dto.getNewPassword())) {
            result.rejectValue("confirmNewPassword", "error.confirmNewPassword", "Nhắc lại mật khẩu mới không đúng");
        }

        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            List<FieldError> errorsList = result.getFieldErrors();
            for (FieldError error : errorsList ) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            responseObject.setErrorMessages(errors);
            responseObject.setStatus("fail");
            errors = null;
        } else {
            userService.changePassword( dto.getNewPassword(), currentUserSystem);
            responseObject.setStatus("success");
        }

        return responseObject;
    }

    public UserSystem getSessionUser(HttpServletRequest request) {
        return (UserSystem) request.getSession().getAttribute("loggedInUser");
    }
}
