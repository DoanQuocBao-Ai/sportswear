package com.store.sportswear.Repository;

import com.store.sportswear.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByNameRole(String name);
}
