package com.sarisite.byOwner.repository;

import com.sarisite.byOwner.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model,Integer> {
    Model findByName(String name);
    boolean existsByName(String name);
}
