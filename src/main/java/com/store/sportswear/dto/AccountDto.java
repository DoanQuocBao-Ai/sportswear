package com.store.sportswear.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class AccountDto {
    private String id;
    @NotEmpty(message = "Địa chỉ mail không được trống")
    @Email(message = "Phải nhập đúng địa chỉ email.")
    private String email;

    @Length(min = 8,max = 32, message = "Mật khẩu phải tử 8 - 32 kí tự.")
    private String password;
    private String confirmPassword;
    
    @NotEmpty(message = "Địa chỉ không được để trống.")
    private String address;
    @NotEmpty(message = "Họ tên không được để trống.")
    private String name;
    @NotEmpty(message = "Số điện thoại không được để trống.")
    private String phone;
    private String role_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}
