package com.sarisite.byOwner.service;

import com.sarisite.byOwner.dto.CarDto.*;
import com.sarisite.byOwner.exeptions.CarNotFoundExeption;
import com.sarisite.byOwner.model.Car;
import com.sarisite.byOwner.repository.BrandRepository;
import com.sarisite.byOwner.repository.CarRepository;
import com.sarisite.byOwner.repository.ColorRepository;
import com.sarisite.byOwner.repository.ModelRepository;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final BrandRepository brandRepository;
    private final ColorRepository colorRepository;
    private final ModelRepository modelRepository;

    public CarService(CarRepository carRepository, BrandRepository brandRepository, ColorRepository colorRepository, ModelRepository modelRepository) {
        this.carRepository = carRepository;
        this.brandRepository = brandRepository;
        this.colorRepository = colorRepository;
        this.modelRepository = modelRepository;
    }

    public List<GetAllCarDto> getAllCar() {
        List<Car> carList = carRepository.findAll();
        List<GetAllCarDto> getAllCarDto = carList.stream().map(car -> {
            GetAllCarDto carDto = new GetAllCarDto();

            carDto.setId(car.getId());
            carDto.setKm(car.getKm());
            carDto.setYear(car.getYear());
            carDto.setModel(car.getModel().getName());
            carDto.setColor(car.getColor().getName());
            carDto.setBrand(car.getBrand().getName());
            carDto.setPrice(car.getPrice());
            carDto.setDateTime(car.getDateTime().toString());

            return carDto;
        }).collect(Collectors.toList());

        return getAllCarDto;
    }

    public CreateCarDto createCar(CreateCarDto createCarDto) {
        String brandName = createCarDto.getBrand().toUpperCase();
        if (brandName.isEmpty() || brandName.isBlank()) {
            throw new CarNotFoundExeption("Brand is not valid!");
        }
        if (!brandRepository.existsByName(createCarDto.getBrand().toUpperCase())) {
            throw new CarNotFoundExeption("There is not such a brand like: " + brandName);
        }
        String modelName = createCarDto.getModel().toUpperCase();
        if (modelName.isEmpty() || modelName.isBlank()) {
            throw new CarNotFoundExeption("Model is not valid!");
        }
        if (!modelRepository.existsByName(modelName)) {
            throw new CarNotFoundExeption("There is no such a model like: " + modelName);
        }
        String colorName = createCarDto.getColor().toUpperCase();
        if (colorName.isEmpty() || colorName.isBlank()) {
            throw new CarNotFoundExeption("Color is not valid!");
        }
        if (!colorRepository.existsByName(colorName)) {
            throw new CarNotFoundExeption("There is no such a color like: " + colorName);
        }
        Integer getYear = createCarDto.getYear();
        int currentYear = Year.now().getValue();
        if (getYear < 1980 || getYear > currentYear + 1) {
            throw new CarNotFoundExeption("Year is not valid!");
        }
        if (createCarDto.getKm() < 0) {
            throw new CarNotFoundExeption("Km is not valid!");
        }

        Car car = new Car();

        car.setYear(createCarDto.getYear());
        car.setKm(createCarDto.getKm());
        car.setModel(modelRepository.findByName(modelName));
        car.setBrand(brandRepository.findByName(brandName));
        car.setColor(colorRepository.findByName(colorName));
        car.setPrice(createCarDto.getPrice());

        carRepository.save(car);
        return createCarDto;
    }

    public UpdateCarDto updateCar(Integer id, UpdateCarDto updateCarDto) {
        Car oldCar = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundExeption("Id is not found!"));

        String brandName = updateCarDto.getBrand().toUpperCase();
        if (brandName.isEmpty() || brandName.isBlank()) {
            throw new CarNotFoundExeption("Brand is not valid!");
        }
        if (!brandRepository.existsByName(updateCarDto.getBrand().toUpperCase())) {
            throw new CarNotFoundExeption("There is not such a brand like: " + brandName);
        }
        String modelName = updateCarDto.getModel().toUpperCase();
        if (modelName.isEmpty() || modelName.isBlank()) {
            throw new CarNotFoundExeption("Model is not valid!");
        }
        if (!modelRepository.existsByName(modelName)) {
            throw new CarNotFoundExeption("There is no such a model like: " + modelName);
        }
        String colorName = updateCarDto.getColor().toUpperCase();
        if (colorName.isEmpty() || colorName.isBlank()) {
            throw new CarNotFoundExeption("Color is not valid!");
        }
        if (!colorRepository.existsByName(colorName)) {
            throw new CarNotFoundExeption("There is no such a color like: " + colorName);
        }
        Integer getYear = updateCarDto.getYear();
        int currentYear = Year.now().getValue();
        if (getYear < 1980 || getYear > currentYear + 1) {
            throw new CarNotFoundExeption("Year is not valid!");
        }
        if (updateCarDto.getKm() < 0) {
            throw new CarNotFoundExeption("Km is not valid!");
        }

        oldCar.setBrand(brandRepository.findByName(brandName));
        oldCar.setModel(modelRepository.findByName(modelName));
        oldCar.setColor(colorRepository.findByName(colorName));
        oldCar.setYear(updateCarDto.getYear());
        oldCar.setKm(updateCarDto.getKm());

        carRepository.save(oldCar);

        return updateCarDto;
    }

    public DeleteCarDto deleteCar(Integer id, DeleteCarDto deleteCarDto) {
        Car deletedCar = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundExeption("Id not found!"));
        carRepository.delete(deletedCar);
        deleteCarDto.setMessage("Deleted succesfully..");
        return deleteCarDto;
    }

    public GetByIdCarDto getByIdCar(Integer id, GetByIdCarDto getByIdCarDto) {
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundExeption("Id not found!"));

        getByIdCarDto.setId(car.getId());
        getByIdCarDto.setYear(car.getYear());
        getByIdCarDto.setModel(car.getModel().getName());
        getByIdCarDto.setKm(car.getKm());
        getByIdCarDto.setColor(car.getColor().getName());
        getByIdCarDto.setBrand(car.getBrand().getName());

        return getByIdCarDto;
    }

    public List<GetCarByColorDto> getCarByColor(String color) {
        List<Car> cars = carRepository.findByColorName(color)
                .orElseThrow(() -> new CarNotFoundExeption("There is no car this color" + color));
        List<GetCarByColorDto> allCarByColor = cars.stream()
                .map(car -> {
                    GetCarByColorDto carDto = new GetCarByColorDto();
                    carDto.setColor(car.getColor().getName());
                    carDto.setYear(car.getYear());
                    carDto.setModel(car.getModel().getName());
                    carDto.setKm(car.getKm());
                    carDto.setId(car.getId());
                    carDto.setBrand(car.getBrand().getName());
                    return carDto;
                })
                .collect(Collectors.toList());
        return allCarByColor;
    }

    public List<GetCarByBrandDto> getCarByBrand(String name) {
        List<Car> carList = carRepository.findByBrandName(name);
//                .orElseThrow(()->new CarNotFoundExeption("Hata"));
        List<GetCarByBrandDto> allCarByBrandName = carList.stream()
                .map(car -> {
                    GetCarByBrandDto brandDto = new GetCarByBrandDto();
                    brandDto.setBrand(car.getBrand().getName());
                    brandDto.setYear(car.getYear());
                    brandDto.setModel(car.getModel().getName());
                    brandDto.setKm(car.getKm());
                    brandDto.setId(car.getId());
                    brandDto.setColor(car.getColor().getName());
                    return brandDto;
                })
                .collect(Collectors.toList());
        return allCarByBrandName;
    }

    public List<GetByCarYearDto> getByCarYear(Integer minYear, Integer maxYear) {
        List<Car> carByYear = carRepository.findByYearBetween(minYear, maxYear);
        List<GetByCarYearDto> getByCarYearDtos = carByYear
                .stream()
                .map(car -> {
                    GetByCarYearDto carYearDto = new GetByCarYearDto();
                    carYearDto.setId(car.getId());
                    carYearDto.setYear(car.getYear());
                    carYearDto.setKm(car.getKm());
                    carYearDto.setModel(car.getModel().getName());
                    carYearDto.setColor(car.getColor().getName());
                    carYearDto.setBrand(car.getBrand().getName());
                    return carYearDto;
                })
                .collect(Collectors.toList());
        return getByCarYearDtos;
    }

    public List<GetCarByMaxKmDto> getCarByMaxKm(Double maxKm) {
        List<Car> cars = carRepository.findByKmLessThan(maxKm);
        List<GetCarByMaxKmDto> carByKm = cars
                .stream()
                .map(car -> {
                    GetCarByMaxKmDto carKm = new GetCarByMaxKmDto();
                    carKm.setYear(car.getYear());
                    carKm.setKm(car.getKm());
                    carKm.setId(car.getId());
                    carKm.setBrand(car.getBrand().getName());
                    carKm.setModel(car.getModel().getName());
                    carKm.setColor(car.getColor().getName());
                    return carKm;
                })
                .collect(Collectors.toList());
        return carByKm;
    }

    public List<GetCarByLatestDto> getCarByLatest() {
        List<Car> carList = carRepository.findTop20ByOrderByDateTimeDesc();
        List<GetCarByLatestDto> carLatestDto = carList
                .stream()
                .map(car -> {
                    GetCarByLatestDto carDto = new GetCarByLatestDto();
                    carDto.setId(car.getId());
                    carDto.setKm(car.getKm());
                    carDto.setYear(car.getYear());
                    carDto.setModel(car.getModel().getName());
                    carDto.setColor(car.getColor().getName());
                    carDto.setBrand(car.getBrand().getName());
                    carDto.setPrice(car.getPrice());
                    carDto.setDateTime(car.getDateTime().toString());
                    return carDto;
                }).collect(Collectors.toList());
        return carLatestDto;
    }

    public List<Car> findByBrandName(String name){
        return carRepository.findByBrandName(name);
    }

}



















