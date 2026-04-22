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

package com.crop.app.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;
import com.crop.app.domain.model.Crop;
import com.crop.app.domain.repository.CropRepository;

/**
 * JSON-based crop repository implementation.
 *
 * <p>
 * Loads crop data from a JSON file and provides lookup operations.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 22-04-2026
 */
public class JsonCropRepository implements CropRepository {
    /**
     * List of crops to manage.
     */
    private final List<Crop> crops;

    /**
     * Creates a crop repository with a pre-loaded list of crops.
     *
     * @param crops the list of crops to manage
     */
    public JsonCropRepository(List<Crop> crops) {
        this.crops = crops != null ? crops : new ArrayList<Crop>();
    }

    /**
     * Retrieves crop information by name.
     *
     * @param cropName the name or id of the crop
     * @return crop instance, null otherwise
     */
    @Override
    public Crop getCrop(String cropNameId) {
        // Validate input
        if (cropNameId == null || cropNameId.isBlank()) {
            return null;
        }

        // Find and return the crop by name or id
        return crops.stream().filter(
                c -> c.getName().equals(cropNameId.trim()) || c.getId().equals(cropNameId.trim()))
                .findFirst().orElse(null);
    }

    /**
     * Finds a crop by its unique identifier.
     *
     * @param cropId the unique identifier of the crop
     * @return the crop if found, null otherwise
     */
    public Crop findById(String cropId) {
        if (cropId == null || cropId.isBlank()) {
            return null;
        }

        return crops.stream().filter(c -> c.getId().equals(cropId.trim())).findFirst().orElse(null);
    }

    /**
     * Finds a crop by its name.
     *
     * @param cropName the name of the crop
     * @return the crop if found, null otherwise
     */
    @Override
    public Crop findByName(String name) {
        if (name == null || name.isBlank()) {
            return null;
        }

        return crops.stream().filter(c -> c.getName().equals(name.trim())).findFirst().orElse(null);
    }

    /**
     * Retrieves a list of all crop names available in the repository.
     *
     * @return a list of crop names
     */
    public List<String> getAllCropNames() {
        return crops.stream().map(Crop::getName).toList();
    }

}
