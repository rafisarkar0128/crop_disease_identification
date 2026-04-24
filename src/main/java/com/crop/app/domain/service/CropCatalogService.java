package com.crop.app.domain.service;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import com.crop.app.domain.model.Crop;
import com.crop.app.domain.model.Disease;
import com.crop.app.infrastructure.loader.MetadataIndexLoader;
import com.crop.app.infrastructure.mapper.CropMetadataMapper;

public final class CropCatalogService {

    private final List<Crop> cropCatalog;

    private String normalize(String query) {
        return query == null ? "" : query.trim().toLowerCase(Locale.ROOT);
    }

    public CropCatalogService() {
        this.cropCatalog = MetadataIndexLoader.loadActiveMetadataFiles().stream()
                .map(CropMetadataMapper::mapFromJson).toList();
    }

    public Crop findCropByNameOrId(String cropNameId) {
        if (cropNameId == null || cropNameId.isBlank()) {
            return null;
        }

        String normalized = normalize(cropNameId);

        return cropCatalog.stream().filter(crop -> normalize(crop.getName()).equals(normalized)
                || normalize(crop.getId()).equals(normalized)).findFirst().orElse(null);
    }

    public List<Crop> findMatchingCrops(String query, int maxResults) {
        String normalizedQuery = normalize(query);

        if (normalizedQuery.isEmpty()) {
            return List.of();
        }

        return cropCatalog.stream().filter(crop -> matchesQuery(crop, normalizedQuery))
                .sorted(Comparator.comparing(Crop::getName, String.CASE_INSENSITIVE_ORDER))
                .limit(maxResults).toList();
    }

    public List<String> collectSymptoms(Crop crop) {
        Set<String> symptoms = new LinkedHashSet<>();

        if (crop == null || crop.getDiseases() == null) {
            return List.of();
        }

        crop.getDiseases().forEach(disease -> {
            if (disease.getSymptoms() != null) {
                disease.getSymptoms().stream()
                        .filter(symptom -> symptom != null && !symptom.isBlank()).map(String::trim)
                        .forEach(symptoms::add);
            }
        });

        return symptoms.stream().sorted(String.CASE_INSENSITIVE_ORDER).toList();
    }

    public List<String> filterSymptoms(List<String> symptomPool, String input, int maxSuggestions) {
        String normalizedInput = normalize(input);

        if (normalizedInput.isEmpty()) {
            return symptomPool.stream().limit(maxSuggestions).toList();
        }

        return symptomPool.stream().filter(symptom -> normalize(symptom).contains(normalizedInput))
                .limit(maxSuggestions).toList();
    }

    public List<Disease> findDiseasesBySymptom(Crop crop, String symptom) {
        if (crop == null || crop.getDiseases() == null || symptom == null || symptom.isBlank()) {
            return List.of();
        }

        String normalizedSymptom = normalize(symptom);
        return crop.getDiseases().stream().filter(disease -> {
            if (disease.getSymptoms() == null) {
                return false;
            }
            return disease.getSymptoms().stream().anyMatch(
                    s -> s != null && !s.isBlank() && normalize(s).equals(normalizedSymptom));
        }).toList();
    }

    private boolean matchesQuery(Crop crop, String normalizedQuery) {
        if (crop == null) {
            return false;
        }

        return containsIgnoreCase(crop.getId(), normalizedQuery)
                || containsIgnoreCase(crop.getName(), normalizedQuery)
                || containsIgnoreCase(crop.getScientificName(), normalizedQuery)
                || containsIgnoreCase(crop.getDescription(), normalizedQuery)
                || crop.getDiseases().stream()
                        .anyMatch(disease -> containsIgnoreCase(disease.getName(), normalizedQuery)
                                || containsIgnoreCase(disease.getCategory(), normalizedQuery)
                                || containsIgnoreCase(disease.getPathogen(), normalizedQuery)
                                || containsIgnoreCase(disease.getDescription(), normalizedQuery));
    }

    private boolean containsIgnoreCase(String value, String normalizedQuery) {
        return value != null && !value.isBlank()
                && value.toLowerCase(Locale.ROOT).contains(normalizedQuery);
    }
}
