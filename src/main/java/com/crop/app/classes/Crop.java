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

import java.io.*;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Crop {

    private String id;
    private String name;
    private String scientificName;
    private String description;
    private ArrayList<Disease> diseases;

    public Crop() {
        this.id = "";
        this.name = "";
        this.scientificName = "";
        this.description = "";
        this.diseases = new ArrayList<>();
    }

    public Crop(String cropFileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode cropMetadata = mapper.readTree(new File(cropFileName));

        this.id = cropMetadata.get("id").asText();
        this.name = cropMetadata.get("name").asText();
        this.scientificName = cropMetadata.get("scientificName").asText();
        this.description = cropMetadata.get("description").asText();
        this.diseases = new ArrayList<>();
        for (JsonNode diseaseNode : cropMetadata.get("diseases")) {
            Disease diseaseObj = mapper.treeToValue(diseaseNode, Disease.class);
            this.diseases.add(diseaseObj);
        }
    }

    public Crop(String id, String name, String scientificName, String description,
            ArrayList<Disease> diseases) {
        this.id = id;
        this.name = name;
        this.scientificName = scientificName;
        this.description = description;
        this.diseases = diseases;
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

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDiseases(ArrayList<Disease> diseases) {
        this.diseases = diseases;
    }

    public ArrayList<Disease> getDiseases() {
        return diseases;
    }

}
