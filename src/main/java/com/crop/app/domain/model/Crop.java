package com.crop.app.domain.model;

import java.util.ArrayList;

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
        this.diseases = new ArrayList<Disease>();
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
