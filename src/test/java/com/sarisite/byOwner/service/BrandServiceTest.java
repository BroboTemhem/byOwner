package com.sarisite.byOwner.service;

import com.sarisite.byOwner.dto.BrandDto.CreateBrandDto;
import com.sarisite.byOwner.dto.BrandDto.GetAllBrandDto;
import com.sarisite.byOwner.dto.BrandDto.GetBrandById;
import com.sarisite.byOwner.dto.BrandDto.UpdateBrandDto;
import com.sarisite.byOwner.exeptions.BrandNotFoundExeption;
import com.sarisite.byOwner.model.Brand;
import com.sarisite.byOwner.repository.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrandServiceTest {

    private BrandService brandService;
    @Mock
    private BrandRepository brandRepository;
    @Mock
    private CarService carService;

    @Captor
    ArgumentCaptor<Brand> argumentCaptor;

    @BeforeEach
    void setUp() {
        brandService = new BrandService(brandRepository, carService);
    }

    @Test
    void testGetAllBrandDto() {
        brandService.getAllBrand();
        verify(brandRepository).findAll();
    }

    @Test
    void testCreateBrandDtoValidRequest() {
        CreateBrandDto brandDto = new CreateBrandDto();
        brandDto.setName("TESTNAME");

        brandService.createBrand(brandDto);

        verify(brandRepository).save(argumentCaptor.capture());

        Brand brand = argumentCaptor.getValue();

        assertNotNull(brand);
        assertEquals(brandDto.getName(), brand.getName());
    }

    @Test
    void whenCreateBrandCalledNotvalidRequest_thenThrowExeption() {
        assertThrows(BrandNotFoundExeption.class, () -> brandService.createBrand(new CreateBrandDto("")));
    }

    @Test
    void whenCreateBrandCalledNotvalidRequest_thenThrowExeption2() {
        when(brandRepository.existsByName("TESTNAME")).thenReturn(true);
        assertThrows(BrandNotFoundExeption.class,
                () -> brandService.createBrand(new CreateBrandDto("TESTNAME")));
    }

    @Test
    void whenUpdateBrandDtoCalledValidRequest() {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setName("TESTOLD");
        brand.setCarBrandList(List.of());

        UpdateBrandDto updateBrandDto = new UpdateBrandDto();
        updateBrandDto.setName("TESTUPDATE");
        Integer id = 1;

        when(brandRepository.findById(id)).thenReturn(Optional.of(brand));
        brandService.updateBrand(id, updateBrandDto);

        verify(brandRepository).save(argumentCaptor.capture());
        Brand capturedBrand = argumentCaptor.getValue();

        assertNotNull(capturedBrand);
        assertEquals(capturedBrand.getName(), updateBrandDto.getName());

    }


    @Test
    void whenDeleteBrandRequestCalledValidId() {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setName("TEST");
        brand.setCarBrandList(List.of());

        when(brandRepository.findById(1)).thenReturn(Optional.of(brand));
        brandService.deleteBrand(1);
        verify(brandRepository).delete(argumentCaptor.capture());

        Brand capturedBrand = argumentCaptor.getValue();

        assertEquals(capturedBrand, brand);
    }

    @Test
    void whenGetByIdBrandCalledValidRequest() {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setName("TEST");
        brand.setCarBrandList(List.of());

        GetBrandById getBrandById = new GetBrandById();
        getBrandById.setId(brand.getId());
        getBrandById.setName(brand.getName());
        getBrandById.setCarBrandList(brand.getCarBrandList());

        when(brandRepository.findById(1)).thenReturn(Optional.of(brand));
        brandService.getByIdBrand(1);
        verify(brandRepository).findById(1);

        assertEquals(brand.getName(), getBrandById.getName());
        assertEquals(brand.getId(), getBrandById.getId());
        assertEquals(brand.getCarBrandList(), getBrandById.getCarBrandList());
    }

    @Test
    void existByNameCalled() {
        brandService.existsByName("test");
        verify(brandRepository).existsByName("test");
    }

}