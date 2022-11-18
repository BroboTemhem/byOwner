package com.sarisite.byOwner.repository;

import com.sarisite.byOwner.model.Car;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Integer> {
    Optional<List<Car>> findByColorName(String name);
    List<Car> findByBrandName(String name);
    List<Car> findByYearBetween(Integer minYear, Integer maxYear);
    List<Car> findByKmLessThan(Double maxKm);
    List<Car> findTop20ByOrderByDateTimeDesc();
}
