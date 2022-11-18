package com.sarisite.byOwner.dto.CarDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarByLatestDto {
    private Integer id;
    private String brand;
    private String model;
    private String color;
    private Integer year;
    private Double km;
    private Double price;
    private String dateTime;
}
