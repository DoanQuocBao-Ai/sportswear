package com.store.sportswear.Repository;

import com.store.sportswear.Entity.Role;
import com.store.sportswear.Entity.UserSystem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<UserSystem,Long> {
    UserSystem findByEmail(String email);
    Page<UserSystem> findByRole(Set<Role>roles, Pageable p);
    List<UserSystem> findByRole(Set<Role>roles);
}
