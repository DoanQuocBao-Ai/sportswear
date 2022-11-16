package com.store.sportswear.Service;

import com.store.sportswear.Dto.AccountDto;
import com.store.sportswear.Entity.Role;
import com.store.sportswear.Entity.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface IUserService {
    User saveUser(User user);
    User updateUser(User user);
    void deleteUserById(Long id);
    User getUserByEmail(String email);
    void changePassword(String newPassword,User user);
    User getUserById(Long id);
    Page<User> getUserByRole(Set<Role> roles, int page);
    List<User> getUserByRule(Set<Role> roles);
    User saveUserForAdmin(AccountDto dto);
    User getUserByConfirmToken(String confirmToken);
}
