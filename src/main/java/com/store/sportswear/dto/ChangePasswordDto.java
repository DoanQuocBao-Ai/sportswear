package com.store.sportswear.dto;

import javax.validation.constraints.NotEmpty;

public class ChangePasswordDto {
    @NotEmpty(message="Nhập mật khẩu cũ.")
    private String oldPassword;
    @NotEmpty(message = "Nhập mật khẩu mới.")
    private String newPassword;
    @NotEmpty(message = "Xác nhận mật khẩu.")
    private String confirmPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    @Override
    public String toString()
    {
        return "ChangePasswordDto [oldPassword="+oldPassword+",newPassword="+newPassword+",confirmPassword="+confirmPassword+"]";
    }
}
