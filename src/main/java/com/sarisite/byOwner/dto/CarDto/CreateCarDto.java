package com.sarisite.byOwner.dto.CarDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarDto {
    private String brand;
    private String model;
    private String color;
    private Integer year;
    private Double km;
    private Double price;
}
