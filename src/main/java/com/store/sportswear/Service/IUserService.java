package com.store.sportswear.Service;

import com.store.sportswear.Dto.AccountDto;
import com.store.sportswear.Entity.Role;
import com.store.sportswear.Entity.UserSystem;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
public interface IUserService {
    public UserSystem saveUserForMember(UserSystem userSystem);
    public UserSystem updateUser(UserSystem userSystem);
    public void deleteUserById(long id);
    public UserSystem getUserByEmail(String email);
    public void changePassword(String newPassword, UserSystem userSystem);
    public UserSystem getUserById(long id);
    public Page<UserSystem> getUserByRole(Set<Role> roles, int page);
    public List<UserSystem> getUserByRole(Set<Role> roles);
    public UserSystem saveUserForAdmin(AccountDto dto);
    public UserSystem getUserByConfirmToken(String confirmToken);
}
