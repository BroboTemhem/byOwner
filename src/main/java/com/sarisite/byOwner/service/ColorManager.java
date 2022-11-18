package com.sarisite.byOwner.service;

import com.sarisite.byOwner.dto.ColorDto.*;
import com.sarisite.byOwner.exeptions.ColorNotFoundExeption;
import com.sarisite.byOwner.model.Color;
import com.sarisite.byOwner.repository.CarRepository;
import com.sarisite.byOwner.repository.ColorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColorManager implements ColorService {
    private final ColorRepository colorRepository;
    private final CarRepository carRepository;

    public ColorManager(ColorRepository colorRepository, CarRepository carRepository) {
        this.colorRepository = colorRepository;
        this.carRepository = carRepository;
    }

    @Override
    public List<GetAllColorDto> getAllColor() {
        List<Color> colors = colorRepository.findAll();
        List<GetAllColorDto> allColorDto = colors
                .stream()
                .map(color -> {
                    GetAllColorDto allColor = new GetAllColorDto();
                    allColor.setId(color.getId());
                    allColor.setName(color.getName());
                    allColor.setCarColorList(color.getCarColorList());
                    return allColor;
                })
                .collect(Collectors.toList());
        return allColorDto;
    }

    @Override
    public CreateColorDto createColor(CreateColorDto createColorDto) {
        String newColorName = createColorDto.getName().toUpperCase();
        if (newColorName.isEmpty() || newColorName.isBlank()) {
            throw new ColorNotFoundExeption("Color name is not valid!");
        }
        if (colorRepository.existsByName(newColorName)) {
            throw new ColorNotFoundExeption("Color name is already exist!");
        }
        Color color = new Color();
        color.setName(newColorName);
        colorRepository.save(color);
        return createColorDto;
    }

    @Override
    public UpdateColorDto updateColor(Integer id, UpdateColorDto updateColorDto) {
        Color color = colorRepository.findById(id)
                .orElseThrow(() -> new ColorNotFoundExeption("There is no color with this id:" + id));
        String newColorName = updateColorDto.getName().toUpperCase();
        if (newColorName.isEmpty() || newColorName.isBlank()) {
            throw new ColorNotFoundExeption("Color name is not valid!");
        }
        if (colorRepository.existsByName(newColorName)) {
            throw new ColorNotFoundExeption("Color name is already exist!");
        }
        color.setName(newColorName);
        colorRepository.save(color);

        return updateColorDto;
    }

    @Override
    public DeleteColorDto deleteColor(Integer id) {
        Color color = colorRepository.findById(id)
                .orElseThrow(() -> new ColorNotFoundExeption("Color id not found!"));
        colorRepository.delete(color);
        DeleteColorDto deletedColor = new DeleteColorDto();
        deletedColor.setName(color.getName());
        return deletedColor;
    }

    @Override
    public GetColorById getColorById(Integer id) {
        Color color = colorRepository.findById(id)
                .orElseThrow(()->new ColorNotFoundExeption("Color id not found!"));
        GetColorById colorDto = new GetColorById();
        colorDto.setId(color.getId());
        colorDto.setName(color.getName());
        colorDto.setCarColorList(color.getCarColorList());
        return colorDto;
    }


}
