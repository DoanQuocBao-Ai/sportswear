package com.store.sportswear.Validator;

import com.store.sportswear.Entity.UserSystem;
import com.store.sportswear.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {
    @Autowired
    private IUserService userService;

    public UserValidator() {
        super();
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserSystem.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserSystem userSystem = (UserSystem) target;

        ValidationUtils.rejectIfEmpty(errors, "email", "error.name", "Họ tên không được bỏ trống");
        ValidationUtils.rejectIfEmpty(errors, "phone", "error.phone", "Số điện thoại không được bỏ trống");
        ValidationUtils.rejectIfEmpty(errors, "address", "error.address", "Địa chỉ không được bỏ trống");

        ValidationUtils.rejectIfEmpty(errors, "email", "error.email", "Email không được trống");

        // check địa chỉ email phù hợp hay không
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        if (!(pattern.matcher(userSystem.getUser_email()).matches())) {
            errors.rejectValue("email", "error.email", "Địa chỉ email không phù hợp");
        }

        // check địa chi email đã được dùng chưa
        if (userService.getUserByEmail(userSystem.getUser_email()) != null) {
            errors.rejectValue("email", "error.email", "Địa chỉ email đã được sử dụng");
        }

        // check password trống hay không
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.password", "Password không được bỏ trống");

        // check confirmPassword trống hay không
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "error.confirmPassword",
                "Nhắc lại mật khẩu không được bỏ trống");

        // check độ dài password (8-32)
        if (userSystem.getUser_password().length() < 8 || userSystem.getUser_password().length() > 32) {
            errors.rejectValue("password", "error.password", "Mật khẩu phải dài 8-32 ký tự");
        }

        // check match pass và confirmPass
        if (!userSystem.getConfirmPassword().equals(userSystem.getUser_password())) {
            errors.rejectValue("confirmPassword", "error.confirmPassword", "Nhắc lại mật khẩu không chính xác");
        }
    }
}
