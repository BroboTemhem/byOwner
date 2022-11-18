package com.sarisite.byOwner.dto.BrandDto;

import com.sarisite.byOwner.model.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBrandById {
    private Integer id;
    private String name;
    private List<Car> carBrandList;
}
