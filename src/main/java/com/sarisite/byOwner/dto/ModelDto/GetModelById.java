package com.sarisite.byOwner.dto.ModelDto;

import com.sarisite.byOwner.model.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetModelById {
    private Integer id;
    private String name;
    private List<Car> carModelList = new ArrayList<>();
}
