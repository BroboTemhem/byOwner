package com.sarisite.byOwner.service;

import com.sarisite.byOwner.dto.CarDto.*;

import java.util.List;

public interface CarService {
    List<GetAllCarDto> getAllCar();

    CreateCarDto createCar(CreateCarDto createCarDto);

    UpdateCarDto updateCar(Integer id, UpdateCarDto updateCarDto);

    DeleteCarDto deleteCar(Integer id, DeleteCarDto deleteCarDto);

    GetByIdCarDto getByIdCar(Integer id, GetByIdCarDto getByIdCarDto);

    List<GetCarByColorDto> getCarByColor(String color);

    List<GetCarByBrandDto> getCarByBrand(String name);

    List<GetByCarYearDto> getByCarYear(Integer minYear, Integer maxYear);

    List<GetCarByMaxKmDto> getCarByMaxKm(Double maxKm);

    List<GetCarByLatestDto> getCarByLatest();

}
