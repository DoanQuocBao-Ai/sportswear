package com.store.sportswear.Service.implement;

import com.store.sportswear.Dto.AccountDto;
import com.store.sportswear.Entity.Role;
import com.store.sportswear.Entity.UserSystem;
import com.store.sportswear.Repository.RoleRepository;
import com.store.sportswear.Repository.UserRepository;
import com.store.sportswear.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    public UserServiceImpl() {
        super();
    }

    @Override
    public UserSystem saveUserForMember(UserSystem userSystem) {
        userSystem.setUser_password(bCryptPasswordEncoder.encode(userSystem.getUser_password()));
        Set<Role> roleSet=new HashSet<>();
        roleSet.add(roleRepository.findByNameRole("ROLE_MEMBER"));
        userSystem.setRole(roleSet);
        return repository.save(userSystem);
    }

    @Override
    public UserSystem updateUser(UserSystem userSystem) {
        return repository.save(userSystem);
    }

    @Override
    public void deleteUserById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UserSystem getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public void changePassword(String newPassword, UserSystem userSystem) {
        userSystem.setUser_password(bCryptPasswordEncoder.encode(newPassword));
        repository.save(userSystem);
    }

    @Override
    public UserSystem getUserById(Long id) {
        UserSystem userSystem =repository.findById(id).get();
        return userSystem;
    }

    @Override
    public Page<UserSystem> getUserByRole(Set<Role> roles, int page) {
        return repository.findByRole(roles, PageRequest.of(page - 1, 6));
    }

    @Override
    public List<UserSystem> getUserByRole(Set<Role> roles) {
        return repository.findByRole(roles);
    }

    @Override
    public UserSystem saveUserForAdmin(AccountDto dto) {
        UserSystem userSystem =new UserSystem();
        userSystem.setUser_name(dto.getName());
        userSystem.setUser_address(dto.getAddress());
        userSystem.setUser_email(dto.getEmail());
        userSystem.setUser_phone(dto.getPhone());
        userSystem.setUser_password(bCryptPasswordEncoder.encode(dto.getPassword()));
        Set<Role> roleSet =new HashSet<>();
        roleSet.add(roleRepository.findByNameRole(dto.getRole_name()));
        userSystem.setRole(roleSet);
        return repository.save(userSystem);
    }

    @Override
    public UserSystem getUserByConfirmToken(String confirmToken) {
        return null;
    }

}
