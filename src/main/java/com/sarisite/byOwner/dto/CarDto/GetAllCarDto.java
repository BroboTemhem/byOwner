package com.sarisite.byOwner.dto.CarDto;

import com.sarisite.byOwner.model.Brand;
import com.sarisite.byOwner.model.Color;
import com.sarisite.byOwner.model.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCarDto {
    private Integer id;
    private String brand;
    private String model;
    private String color;
    private Integer year;
    private Double km;
    private Double price;
    private String dateTime;
}
