package com.store.sportswear.Repository;

import com.store.sportswear.Entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categories,Long> {

}
