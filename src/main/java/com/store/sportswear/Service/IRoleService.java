package com.store.sportswear.Service;

import com.store.sportswear.Entity.Role;

import java.util.List;

public interface IRoleService {
    Role getRoleByName(String name);
    List<Role>getAllRole();
}
