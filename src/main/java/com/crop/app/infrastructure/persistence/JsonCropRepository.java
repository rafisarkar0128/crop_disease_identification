package com.crop.app.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;
import com.crop.app.domain.model.Crop;
import com.crop.app.domain.repository.CropRepository;

public class JsonCropRepository implements CropRepository {

    private final List<Crop> crops;

    public JsonCropRepository(List<Crop> crops) {
        this.crops = crops != null ? crops : new ArrayList<Crop>();
    }

    @Override
    public Crop getCrop(String cropNameId) {
        if (cropNameId == null || cropNameId.isBlank()) {
            return null;
        }

        return crops.stream().filter(
                c -> c.getName().equals(cropNameId.trim()) || c.getId().equals(cropNameId.trim()))
                .findFirst().orElse(null);
    }

    public Crop findById(String cropId) {
        if (cropId == null || cropId.isBlank()) {
            return null;
        }

        return crops.stream().filter(c -> c.getId().equals(cropId.trim())).findFirst().orElse(null);
    }

    @Override
    public Crop findByName(String name) {
        if (name == null || name.isBlank()) {
            return null;
        }

        return crops.stream().filter(c -> c.getName().equals(name.trim())).findFirst().orElse(null);
    }

    public List<String> getAllCropNames() {
        return crops.stream().map(Crop::getName).toList();
    }
}
