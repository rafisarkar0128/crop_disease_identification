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

package com.crop.app.domain.repository;

import java.util.List;
import com.crop.app.domain.model.Crop;

/**
 * Repository interface for crop disease data access operations.
 *
 * <p>
 * Defines methods for retrieving crop disease information and metadata.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 22-04-2026
 */
public interface DiseaseRepository {
    /**
     * Retrieves crop disease information by name.
     *
     * @param disease the name or id of the crop disease
     * @return crop disease instance, null otherwise
     */
    Crop getDisease(String disease);

    /**
     * Finds a crop disease by its unique identifier.
     *
     * @param diseaseId the unique identifier of the crop disease
     * @return the crop disease if found, null otherwise
     */
    Crop findById(String diseaseId);

    /**
     * Finds a crop disease by its name.
     *
     * @param diseaseName the name of the crop disease
     * @return the crop disease if found, null otherwise
     */
    Crop findByName(String diseaseName);

    /**
     * Retrieves a list of all crop disease names available in the repository.
     *
     * @return a list of crop disease names
     */
    List<String> getAllDiseaseNames();
}
