package com.store.sportswear.Repository;

import com.store.sportswear.Entity.Role;
import com.store.sportswear.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    Page<User> findByRole(Set<Role>roles, Pageable p);
    List<User> findByRole(Set<Role>roles);
}
