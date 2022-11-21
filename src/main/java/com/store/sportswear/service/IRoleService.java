package com.store.sportswear.service;

import com.store.sportswear.entity.Role;

import java.util.List;

public interface IRoleService {
    Role getRoleByName(String name);
    List<Role>getAllRole();
}
