package com.crop.app.domain.repository;

import java.util.List;
import com.crop.app.domain.model.Crop;

public interface CropRepository {

    Crop getCrop(String cropNameId);

    Crop findById(String cropId);

    Crop findByName(String cropName);

    List<String> getAllCropNames();
}
