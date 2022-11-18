package com.sarisite.byOwner.repository;

import com.sarisite.byOwner.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Integer> {
    Brand findByName(String name);
    boolean existsByName(String name);
}
