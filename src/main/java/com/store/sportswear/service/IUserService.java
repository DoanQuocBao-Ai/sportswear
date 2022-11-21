package com.store.sportswear.service;

import com.store.sportswear.dto.AccountDto;
import com.store.sportswear.entity.Role;
import com.store.sportswear.entity.UserSystem;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
public interface IUserService {
    public UserSystem saveUserForMember(UserSystem userSystem);
    public UserSystem updateUser(UserSystem userSystem);
    public void deleteUserById(int id);
    public UserSystem getUserByEmail(String email);
    public void changePassword(String newPassword, UserSystem userSystem);
    public UserSystem getUserById(int id);
    public Page<UserSystem> getUserByRole(Set<Role> roles, int page);
    public List<UserSystem> getUserByRole(Set<Role> roles);
    public UserSystem saveUserForAdmin(AccountDto dto);
    public UserSystem getUserByConfirmToken(String confirmToken);
}
