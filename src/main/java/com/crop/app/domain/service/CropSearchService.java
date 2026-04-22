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

import java.util.List;
import com.crop.app.domain.model.Crop;
import com.crop.app.domain.repository.CropRepository;

/**
 * Service for crop search and information retrieval operations.
 *
 * <p>
 * This service encapsulates business logic for crop-related use cases.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 22-04-2026
 */
public class CropSearchService {
    /**
     * The crop repository implementation.
     */
    private final CropRepository cropRepository;

    /**
     * Creates a crop search service instance.
     *
     * @param cropRepository the crop repository implementation to use for data access
     */
    public CropSearchService(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }

    /**
     * Searches for crop information based on the provided query.
     *
     * @param query the search query, which can be a crop name or id
     * @return crop information if found, null otherwise
     */
    public Crop searchCrop(String query) {
        // Search by crop name first
        Crop cropInfo = cropRepository.findByName(query);
        if (cropInfo != null && cropInfo instanceof Crop) {
            return cropInfo;
        }

        // If not found, search by crop id
        cropInfo = cropRepository.findById(query);
        if (cropInfo != null && cropInfo instanceof Crop) {
            return cropInfo;
        }

        // If still not found, return null
        return null;
    }

    /**
     * Retrieves a list of all available crop names.
     *
     * @return a list of crop names
     */
    public List<String> getAllCropNames() {
        return cropRepository.getAllCropNames();
    }
}
