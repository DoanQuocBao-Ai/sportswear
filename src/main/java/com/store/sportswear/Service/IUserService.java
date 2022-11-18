package com.store.sportswear.Service;

import com.store.sportswear.Dto.AccountDto;
import com.store.sportswear.Entity.Role;
import com.store.sportswear.Entity.UserSystem;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface IUserService {
    UserSystem saveUserForMember(UserSystem userSystem);
    UserSystem updateUser(UserSystem userSystem);
    void deleteUserById(Long id);
    UserSystem getUserByEmail(String email);
    void changePassword(String newPassword, UserSystem userSystem);
    UserSystem getUserById(Long id);
    Page<UserSystem> getUserByRole(Set<Role> roles, int page);
    List<UserSystem> getUserByRole(Set<Role> roles);
    UserSystem saveUserForAdmin(AccountDto dto);
    UserSystem getUserByConfirmToken(String confirmToken);
}
