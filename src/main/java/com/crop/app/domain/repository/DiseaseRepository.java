package com.crop.app.domain.repository;

import java.util.List;
import com.crop.app.domain.model.Crop;

public interface DiseaseRepository {

    Crop getDisease(String disease);

    Crop findById(String diseaseId);

    Crop findByName(String diseaseName);

    List<String> getAllDiseaseNames();
}
