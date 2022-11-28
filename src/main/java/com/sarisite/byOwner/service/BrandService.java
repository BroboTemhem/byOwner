package com.sarisite.byOwner.service;

import com.sarisite.byOwner.dto.BrandDto.*;
import com.sarisite.byOwner.exeptions.BrandNotFoundExeption;
import com.sarisite.byOwner.model.Brand;
import com.sarisite.byOwner.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService {
    private final BrandRepository brandRepository;
    private final CarService carService;

    public BrandService(BrandRepository brandRepository, CarService carService) {
        this.brandRepository = brandRepository;
        this.carService = carService;
    }

    public List<GetAllBrandDto> getAllBrand() {
        List<Brand> brands = brandRepository.findAll();
        List<GetAllBrandDto> getAllBrandDtos = brands
                .stream()
                .map(brand -> {
                    GetAllBrandDto brandDto = new GetAllBrandDto();
                    brandDto.setId(brand.getId());
                    brandDto.setName(brand.getName());
                    brandDto.setCarBrandList(carService.findByBrandName(brand.getName()));
                    return brandDto;
                })
                .collect(Collectors.toList());
        return getAllBrandDtos;
    }

    public CreateBrandDto createBrand(CreateBrandDto createBrandDto) {
        String brandName = createBrandDto.getName().toUpperCase();
        if (brandRepository.existsByName(brandName)) {
            throw new BrandNotFoundExeption("Name is already in there!");
        }
        if (brandName == null || brandName.trim().isEmpty()) {
            throw new BrandNotFoundExeption("Name is not valid!");
        }
        Brand brand = new Brand();
        brand.setName(brandName);
        brandRepository.save(brand);
        return createBrandDto;
    }

    public UpdateBrandDto updateBrand(Integer id, UpdateBrandDto updateBrandDto) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundExeption("Brand not found this id: " + id));
        String newName = updateBrandDto.getName().toUpperCase();
        if (brandRepository.existsByName(newName)) {
            throw new BrandNotFoundExeption("This name already exist!");
        }
        brand.setName(newName);
        brandRepository.save(brand);
        return updateBrandDto;
    }

    public DeleteBrandDto deleteBrand(Integer id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundExeption("Brand not found this id: " + id));
        brandRepository.delete(brand);
        DeleteBrandDto delBrand = new DeleteBrandDto();
        delBrand.setName(brand.getName());
        return delBrand;
    }

    public GetBrandById getByIdBrand(Integer id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundExeption("Brand not found this id: " + id));

        GetBrandById byIdBrand = new GetBrandById();
        byIdBrand.setId(brand.getId());
        byIdBrand.setName(brand.getName());
        byIdBrand.setCarBrandList(brand.getCarBrandList());

        return byIdBrand;
    }

    boolean existsByName(String name){
        return brandRepository.existsByName(name);
    }


}
