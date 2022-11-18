package com.sarisite.byOwner.repository;

import com.sarisite.byOwner.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color,Integer> {
    Color findByName(String name);
    boolean existsByName(String name);
}
