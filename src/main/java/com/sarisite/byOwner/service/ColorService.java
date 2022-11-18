package com.sarisite.byOwner.service;


import com.sarisite.byOwner.dto.ColorDto.*;

import java.util.List;

public interface ColorService {
    List<GetAllColorDto> getAllColor();
    CreateColorDto createColor(CreateColorDto createColorDto);
    UpdateColorDto updateColor(Integer id, UpdateColorDto updateColorDto);
    DeleteColorDto deleteColor(Integer id);
    GetColorById getColorById(Integer id);
}
