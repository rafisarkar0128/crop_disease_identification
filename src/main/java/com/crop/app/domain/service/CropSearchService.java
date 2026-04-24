package com.crop.app.domain.service;

import java.util.List;
import com.crop.app.domain.model.Crop;
import com.crop.app.domain.repository.CropRepository;

public class CropSearchService {

    private final CropRepository cropRepository;

    public CropSearchService(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }

    public Crop searchCrop(String query) {
        Crop cropInfo = cropRepository.findByName(query);
        if (cropInfo != null && cropInfo instanceof Crop) {
            return cropInfo;
        }

        cropInfo = cropRepository.findById(query);
        if (cropInfo != null && cropInfo instanceof Crop) {
            return cropInfo;
        }

        return null;
    }

    public List<String> getAllCropNames() {
        return cropRepository.getAllCropNames();
    }
}
