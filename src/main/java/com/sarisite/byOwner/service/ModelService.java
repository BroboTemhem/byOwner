package com.sarisite.byOwner.service;

import com.sarisite.byOwner.dto.ColorDto.GetColorById;
import com.sarisite.byOwner.dto.ModelDto.*;
import com.sarisite.byOwner.model.Model;

import java.util.List;

public interface ModelService {
    List<GetAllModelDto> getAllModel();
    CreateModelDto createModel(CreateModelDto createModelDto);
    UpdateModelDto updateModel(Integer id,UpdateModelDto updateModelDto);
    DeleteModelDto deleteModel(Integer id);
    GetModelById getModelById(Integer id);

}
