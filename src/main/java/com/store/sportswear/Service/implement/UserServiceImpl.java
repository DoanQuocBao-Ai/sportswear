package com.store.sportswear.Service.implement;

import com.store.sportswear.Dto.AccountDto;
import com.store.sportswear.Entity.Role;
import com.store.sportswear.Entity.User;
import com.store.sportswear.Repository.UserRepository;
import com.store.sportswear.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepository repository;

    public UserServiceImpl() {
        super();
    }

    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return repository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public void changePassword(String newPassword, User user) {

    }

    @Override
    public User getUserById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Page<User> getUserByRole(Set<Role> roles, int page) {
        return null;
    }

    @Override
    public List<User> getUserByRule(Set<Role> roles) {
        return null;
    }

    @Override
    public User saveUserForAdmin(AccountDto dto) {
        return null;
    }

    @Override
    public User getUserByConfirmToken(String confirmToken) {
        return null;
    }
}
