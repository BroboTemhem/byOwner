package com.sarisite.byOwner.service;

import com.sarisite.byOwner.dto.ModelDto.*;
import com.sarisite.byOwner.exeptions.ColorNotFoundExeption;
import com.sarisite.byOwner.exeptions.ModelNotFoundExeption;
import com.sarisite.byOwner.model.Model;
import com.sarisite.byOwner.repository.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelService {

    private final ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public List<GetAllModelDto> getAllModel() {
        List<Model> models = modelRepository.findAll();
        List<GetAllModelDto> gettAllModel = models.stream().map(model -> {
            GetAllModelDto modelDto = new GetAllModelDto();
            modelDto.setId(model.getId());
            modelDto.setName(model.getName());
            modelDto.setCarModelList(model.getCarModelList());
            return modelDto;
        }).collect(Collectors.toList());
        return gettAllModel;
    }

    public CreateModelDto createModel(CreateModelDto createModelDto) {
        String newModelName = createModelDto.getName().toUpperCase();
        if (newModelName.isEmpty() || newModelName.isBlank()) {
            throw new ModelNotFoundExeption("Model name is not valid!");
        }
        if (modelRepository.existsByName(newModelName)) {
            throw new ModelNotFoundExeption("Model name is already exist!");
        }
        Model model = new Model();
        model.setName(newModelName);
        modelRepository.save(model);
        return createModelDto;
    }

    public UpdateModelDto updateModel(Integer id, UpdateModelDto updateModelDto) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundExeption("There is no model with this id:" + id));
        String newModelName = updateModelDto.getName().toUpperCase();
        if (newModelName.isEmpty() || newModelName.isBlank()) {
            throw new ModelNotFoundExeption("Model name is not valid!");
        }
        if (modelRepository.existsByName(newModelName)) {
            throw new ModelNotFoundExeption("Model name is already exist!");
        }
        model.setName(newModelName);
        modelRepository.save(model);
        return updateModelDto;
    }

    public DeleteModelDto deleteModel(Integer id) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundExeption("Model id not found!"));
        modelRepository.delete(model);
        DeleteModelDto deletedModel = new DeleteModelDto();
        deletedModel.setName(model.getName());
        return deletedModel;
    }

    public GetModelById getModelById(Integer id) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new ColorNotFoundExeption("Color id not found!"));
        GetModelById getModelById = new GetModelById();
        getModelById.setId(model.getId());
        getModelById.setName(model.getName());
        getModelById.setCarModelList(model.getCarModelList());
        return getModelById;
    }
}
