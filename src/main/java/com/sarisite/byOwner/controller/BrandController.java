package com.sarisite.byOwner.controller;

import com.sarisite.byOwner.dto.BrandDto.*;
import com.sarisite.byOwner.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/brand")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public ResponseEntity<List<GetAllBrandDto>> getAllBrand(){
        return new ResponseEntity<>(brandService.getAllBrand(),OK);
    }

    @PostMapping
    public ResponseEntity<CreateBrandDto> createBrand(@RequestBody CreateBrandDto createBrandDto){
        return new ResponseEntity<>(brandService.createBrand(createBrandDto), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateBrandDto> updateBrand(@PathVariable Integer id, @RequestBody UpdateBrandDto updateBrandDto){
        return new ResponseEntity<>(brandService.updateBrand(id,updateBrandDto),ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteBrandDto> deleteBrand(@PathVariable Integer id){
        return new ResponseEntity<>(brandService.deleteBrand(id),OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetBrandById> getBrandById(@PathVariable Integer id){
        return new ResponseEntity<>(brandService.getByIdBrand(id),OK);
    }
}
