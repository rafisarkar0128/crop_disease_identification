/*
 * Crop Disease Identification
 *
 * Copyright 2026-Present Md. Rafi Sarkar (rafisarkar0128), and contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.crop.app.domain.service;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import com.crop.app.domain.model.Crop;
import com.crop.app.infrastructure.loader.MetadataIndexLoader;
import com.crop.app.infrastructure.mapper.CropMetadataMapper;

/**
 * Service that provides crop catalog lookup and symptom suggestion features.
 *
 * <p>
 * Loads active crop metadata once and exposes utilities used by UI flows such as crop search, crop
 * selection by id/name, and symptom auto-complete.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 23-04-2026
 */
public final class CropCatalogService {
    /**
     * In-memory list of active crop metadata loaded from resources.
     */
    private final List<Crop> cropCatalog;

    /**
     * Creates the service and loads active crop metadata.
     */
    public CropCatalogService() {
        this.cropCatalog = MetadataIndexLoader.loadActiveMetadataFiles().stream()
                .map(CropMetadataMapper::mapFromJson).toList();
    }

    /**
     * Finds a crop by exact name or id.
     *
     * @param cropNameId crop name or crop id
     * @return matching crop metadata, or null if not found
     */
    public Crop findCropByNameOrId(String cropNameId) {
        if (cropNameId == null || cropNameId.isBlank()) {
            return null;
        }

        String normalized = normalize(cropNameId);

        return cropCatalog.stream().filter(crop -> normalize(crop.getName()).equals(normalized)
                || normalize(crop.getId()).equals(normalized)).findFirst().orElse(null);
    }

    /**
     * Finds crops that match a query across crop and disease fields.
     *
     * @param query search query
     * @param maxResults result limit
     * @return matched crops sorted by crop name
     */
    public List<Crop> findMatchingCrops(String query, int maxResults) {
        String normalizedQuery = normalize(query);

        if (normalizedQuery.isEmpty()) {
            return List.of();
        }

        return cropCatalog.stream().filter(crop -> matchesQuery(crop, normalizedQuery))
                .sorted(Comparator.comparing(Crop::getName, String.CASE_INSENSITIVE_ORDER))
                .limit(maxResults).toList();
    }

    /**
     * Collects unique symptom suggestions for a crop.
     *
     * @param crop selected crop
     * @return unique symptom list sorted alphabetically
     */
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

    /**
     * Filters symptom suggestions based on user text input.
     *
     * @param symptomPool all available symptoms
     * @param input user-entered text
     * @param maxSuggestions suggestion limit
     * @return filtered suggestion list
     */
    public List<String> filterSymptoms(List<String> symptomPool, String input, int maxSuggestions) {
        String normalizedInput = normalize(input);

        if (normalizedInput.isEmpty()) {
            return symptomPool.stream().limit(maxSuggestions).toList();
        }

        return symptomPool.stream().filter(symptom -> normalize(symptom).contains(normalizedInput))
                .limit(maxSuggestions).toList();
    }

    /**
     * Checks if a crop matches a search query by looking for the query in crop and disease fields.
     *
     * @param crop the crop to check
     * @param normalizedQuery the search query, normalized to lower case and trimmed
     * @return true if the crop matches the query, false otherwise
     */
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

    /**
     * Helper to check if a string contains a query substring, ignoring case and treating null/blank
     * as non-matching.
     *
     * @param value the string to check
     * @param normalizedQuery the query to match
     * @return true if the string contains the query, false otherwise
     */
    private boolean containsIgnoreCase(String value, String normalizedQuery) {
        return value != null && !value.isBlank()
                && value.toLowerCase(Locale.ROOT).contains(normalizedQuery);
    }

    /**
     * Normalizes a string for case-insensitive comparison by trimming and converting to lower case.
     *
     * @param query the string to normalize
     * @return the normalized string
     */
    private String normalize(String query) {
        return query == null ? "" : query.trim().toLowerCase(Locale.ROOT);
    }
}
