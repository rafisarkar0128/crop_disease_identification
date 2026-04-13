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

package com.crop.app.domain.model;

import java.util.ArrayList;

/***
 * Crop class represents a crop with its details and associated diseases. It contains properties
 * such as id, name, scientific name, description, and a list of diseases that affect the crop. The
 * class provides constructors, getters, and setters for these properties. It serves as a data model
 * for representing crop information in the application.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 14-04-2026
 */
public class Crop {

    private String id;
    private String name;
    private String scientificName;
    private String description;
    private ArrayList<Disease> diseases;

    /**
     * Default constructor initializes the Crop object with default values. The id, name, scientific
     * name, and description are set to empty strings, and the diseases list is initialized as an
     * empty ArrayList. This constructor allows for creating a Crop object with default values,
     * which can be later modified using the setter methods.
     */
    public Crop() {
        this.id = "";
        this.name = "";
        this.scientificName = "";
        this.description = "";
        this.diseases = new ArrayList<Disease>();
    }

    /**
     * Parameterized constructor initializes the Crop object with the provided values for id, name,
     * scientific name, description, and diseases. It allows for creating a Crop object with
     * specific details and associated diseases.
     *
     * @param id the unique identifier for the crop
     * @param name the common name of the crop
     * @param scientificName the scientific name of the crop
     * @param description a brief description of the crop
     * @param diseases a list of diseases that affect the crop
     */
    public Crop(String id, String name, String scientificName, String description,
            ArrayList<Disease> diseases) {
        this.id = id;
        this.name = name;
        this.scientificName = scientificName;
        this.description = description;
        this.diseases = diseases;
    }

    /**
     * Sets the unique identifier for the crop.
     *
     * @param id the unique identifier for the crop
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the unique identifier for the crop.
     *
     * @return the unique identifier for the crop
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the common name of the crop.
     *
     * @param name the common name of the crop
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the common name of the crop.
     *
     * @return the common name of the crop
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the scientific name of the crop.
     *
     * @param scientificName the scientific name of the crop
     */
    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    /**
     * Returns the scientific name of the crop.
     *
     * @return the scientific name of the crop
     */
    public String getScientificName() {
        return scientificName;
    }

    /**
     * Sets the description of the crop.
     *
     * @param description a brief description of the crop
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the description of the crop.
     *
     * @return a brief description of the crop
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the list of diseases that affect the crop.
     *
     * @param diseases a list of diseases that affect the crop
     */
    public void setDiseases(ArrayList<Disease> diseases) {
        this.diseases = diseases;
    }

    /**
     * Returns the list of diseases that affect the crop.
     *
     * @return a list of diseases that affect the crop
     */
    public ArrayList<Disease> getDiseases() {
        return diseases;
    }

}
