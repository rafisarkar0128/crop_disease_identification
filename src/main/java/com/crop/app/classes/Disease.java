/*
 * Crop Disease Identification
 *
 * Copyright 2026-Present Md.Rafi Sarkar(rafisarkar0128), and contributors.
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

package com.crop.app.classes;

import java.util.ArrayList;

public class Disease {

    private String id;
    private String name;
    private String pathogen;
    private String category;
    private String description;
    private String image;
    private ArrayList<String> symptoms;
    private ArrayList<String> treatments;

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

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPathogen(String pathogen) {
        this.pathogen = pathogen;
    }

    public String getPathogen() {
        return pathogen;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setSymptoms(ArrayList<String> symptoms) {
        this.symptoms = symptoms;
    }

    public ArrayList<String> getSymptoms() {
        return symptoms;
    }

    public void setTreatments(ArrayList<String> treatments) {
        this.treatments = treatments;
    }

    public ArrayList<String> getTreatments() {
        return treatments;
    }

}
