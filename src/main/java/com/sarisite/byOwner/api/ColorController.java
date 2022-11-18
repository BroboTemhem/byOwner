package com.sarisite.byOwner.api;

import com.sarisite.byOwner.dto.ColorDto.*;
import com.sarisite.byOwner.service.ColorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/color")
public class ColorController {
    private final ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping
    public ResponseEntity<List<GetAllColorDto>> getAllColor(){
        return new ResponseEntity<>(colorService.getAllColor(), OK);
    }

    @PostMapping
    public ResponseEntity<CreateColorDto> createColor(@RequestBody CreateColorDto createColorDto){
        return new ResponseEntity<>(colorService.createColor(createColorDto),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateColorDto> updateColor(@PathVariable Integer id, @RequestBody UpdateColorDto updateColorDto){
        return new ResponseEntity<>(colorService.updateColor(id,updateColorDto),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteColorDto> deleteColor(@PathVariable Integer id){
        return new ResponseEntity<>(colorService.deleteColor(id), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetColorById> getColorById(@PathVariable Integer id){
        return new ResponseEntity<>(colorService.getColorById(id),OK);
    }

}
