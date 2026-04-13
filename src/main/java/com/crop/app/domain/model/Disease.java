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

/**
 * Disease class represents a disease that affects a crop. It contains properties such as id, name,
 * pathogen, category, description, image, symptoms, and treatments. The class provides
 * constructors, getters, and setters for these properties. It serves as a data model for
 * representing disease information in the application.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 2026-04-14
 */
public class Disease {

    private String id;
    private String name;
    private String pathogen;
    private String category;
    private String description;
    private String image;
    private ArrayList<String> symptoms;
    private ArrayList<String> treatments;

    /**
     * Default constructor initializes the Disease object with default values. The id, name,
     * pathogen, category, description, image, symptoms, and treatments are set to empty strings,
     * and the symptoms and treatments lists are initialized as empty ArrayLists. This constructor
     * allows for creating a Disease object with default values, which can be later modified using
     * the setter methods.
     */
    public Disease() {
        this.id = "";
        this.name = "";
        this.pathogen = "";
        this.category = "";
        this.description = "";
        this.image = "";
        this.symptoms = new ArrayList<>();
        this.treatments = new ArrayList<>();
    }

    /**
     * Parameterized constructor initializes the Disease object with the provided values for id,
     * name, pathogen, category, description, image, symptoms, and treatments. It allows for
     * creating a Disease object with specific details and associated symptoms and treatments.
     *
     * @param id the unique identifier for the disease
     * @param name the common name of the disease
     * @param pathogen the pathogen associated with the disease
     * @param category the category of the disease (e.g., fungal, bacterial, viral)
     * @param description a brief description of the disease
     * @param image the image associated with the disease
     * @param symptoms a list of symptoms associated with the disease
     * @param treatments a list of treatments for the disease
     */
    public Disease(String id, String name, String pathogen, String category, String description,
            String image, ArrayList<String> symptoms, ArrayList<String> treatments) {
        this.id = id;
        this.name = name;
        this.pathogen = pathogen;
        this.category = category;
        this.description = description;
        this.image = image;
        this.symptoms = symptoms;
        this.treatments = treatments;
    }

    /**
     * Sets the unique identifier for the disease.
     *
     * @param id the unique identifier for the disease
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the unique identifier for the disease.
     *
     * @return the unique identifier for the disease
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the common name of the disease.
     *
     * @param name the common name of the disease
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the common name of the disease.
     *
     * @return the common name of the disease
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the pathogen associated with the disease.
     *
     * @param pathogen the pathogen associated with the disease
     */
    public void setPathogen(String pathogen) {
        this.pathogen = pathogen;
    }

    /**
     * Returns the pathogen associated with the disease.
     *
     * @return the pathogen associated with the disease
     */
    public String getPathogen() {
        return pathogen;
    }

    /**
     * Sets the category of the disease.
     *
     * @param category the category of the disease (e.g., fungal, bacterial, viral)
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns the category of the disease.
     *
     * @return the category of the disease (e.g., fungal, bacterial, viral)
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the description of the disease.
     *
     * @param description a brief description of the disease
     */

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the description of the disease.
     *
     * @return a brief description of the disease
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the image associated with the disease.
     *
     * @param image the image associated with the disease
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Returns the image associated with the disease.
     *
     * @return the image associated with the disease
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the list of symptoms associated with the disease.
     *
     * @param symptoms a list of symptoms associated with the disease
     */
    public void setSymptoms(ArrayList<String> symptoms) {
        this.symptoms = symptoms;
    }

    /**
     * Returns the list of symptoms associated with the disease.
     *
     * @return a list of symptoms associated with the disease
     */
    public ArrayList<String> getSymptoms() {
        return symptoms;
    }

    /**
     * Sets the list of treatments associated with the disease.
     *
     * @param treatments a list of treatments associated with the disease
     */
    public void setTreatments(ArrayList<String> treatments) {
        this.treatments = treatments;
    }

    /**
     * Returns the list of treatments associated with the disease.
     *
     * @return a list of treatments associated with the disease
     */
    public ArrayList<String> getTreatments() {
        return treatments;
    }

}
