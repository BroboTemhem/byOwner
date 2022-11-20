package com.sarisite.byOwner.controller;

import com.sarisite.byOwner.dto.ModelDto.*;
import com.sarisite.byOwner.service.ModelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/model")
public class ModelController {
    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping
    public ResponseEntity<List<GetAllModelDto>> getAllModel(){
        return new ResponseEntity<>(modelService.getAllModel(), OK);
    }

    @PostMapping
    public ResponseEntity<CreateModelDto> createModel(@RequestBody CreateModelDto createModelDto){
        return new ResponseEntity<>(modelService.createModel(createModelDto),CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateModelDto> updateModel(@PathVariable Integer id, @RequestBody UpdateModelDto updateModelDto){
        return new ResponseEntity<>(modelService.updateModel(id,updateModelDto),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteModelDto> deleteModel(@PathVariable Integer id){
        return new ResponseEntity<>(modelService.deleteModel(id), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetModelById> getModelById(@PathVariable Integer id){
        return new ResponseEntity<>(modelService.getModelById(id),OK);
    }
}
