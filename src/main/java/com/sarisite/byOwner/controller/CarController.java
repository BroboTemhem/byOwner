package com.sarisite.byOwner.controller;

import com.sarisite.byOwner.dto.CarDto.*;
import com.sarisite.byOwner.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/car")
public class CarController {
    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<GetAllCarDto> getAllCar(){
        return carService.getAllCar();
    }

    @PostMapping
    public ResponseEntity<CreateCarDto> createCar(@RequestBody CreateCarDto createCarDto){
        return new ResponseEntity<>(carService.createCar(createCarDto), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateCarDto> updateCar(@PathVariable Integer id, @RequestBody UpdateCarDto updateCarDto){
        return new ResponseEntity<>(carService.updateCar(id, updateCarDto), ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteCarDto> deleteCar(@PathVariable Integer id, DeleteCarDto deleteCarDto){
        return new ResponseEntity<>(carService.deleteCar(id, deleteCarDto), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetByIdCarDto> getByIdCar(@PathVariable Integer id, GetByIdCarDto getByIdCar){
        return new ResponseEntity<>(carService.getByIdCar(id, getByIdCar), OK);
    }

    @GetMapping("/color") // color?name=mavi
    public ResponseEntity<List<GetCarByColorDto>> getAllColorCar(@RequestParam String name){
        return new ResponseEntity<>(carService.getCarByColor(name.toUpperCase()),OK);
    }

    @GetMapping("/brand") // api/car/brand?name=bmw
    public ResponseEntity<List<GetCarByBrandDto>> getCarByBrand(@RequestParam String name){
        return new ResponseEntity<>(carService.getCarByBrand(name.toUpperCase()),OK);
    }

    @GetMapping("/year") // ** api/car/year?minYear=2015&maxYear=2016
    public ResponseEntity<List<GetByCarYearDto>> getByCarYear(@RequestParam Integer minYear, Integer maxYear){
        return new ResponseEntity<>(carService.getByCarYear(minYear, maxYear),OK);
    }

    @GetMapping("/km") // car/km?maxKm=123
    public ResponseEntity<List<GetCarByMaxKmDto>> getCarByMaxKm(@RequestParam Double maxKm){
        return new ResponseEntity<>(carService.getCarByMaxKm(maxKm),OK);
    }

    @GetMapping("/latest") // Sorted by date time.
    public ResponseEntity<List<GetCarByLatestDto>> getCarLatest(){
        return new ResponseEntity<>(carService.getCarByLatest(),OK);
    }

}
