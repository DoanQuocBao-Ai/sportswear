package com.store.sportswear.Service.implement;

import com.store.sportswear.Entity.Role;
import com.store.sportswear.Repository.RoleRepository;
import com.store.sportswear.Service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoleServiceImpl implements IRoleService {
    @Autowired
    RoleRepository repository;

    public RoleServiceImpl() {
        super();
    }

    @Override
    public Role getRoleByName(String name) {
        return repository.findByNameRole(name);
    }

    @Override
    public List<Role> getAllRole() {
        return repository.findAll();
    }
}
