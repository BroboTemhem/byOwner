package com.sarisite.byOwner.service;

import com.sarisite.byOwner.dto.BrandDto.*;

import java.util.List;

public interface BrandService {

    List<GetAllBrandDto> getAllBrand();

    CreateBrandDto createBrand(CreateBrandDto createBrandDto);

    UpdateBrandDto updateBrand(Integer id, UpdateBrandDto updateBrandDto);

    DeleteBrandDto deleteBrand(Integer id);

    GetBrandById getByIdBrand(Integer id);
}
