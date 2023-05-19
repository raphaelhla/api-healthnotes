package com.ufcg.apihealthnotes.dto;

import com.ufcg.apihealthnotes.entities.Patient;

public class MedicineDTO {

    private String name;
    private String description;

    public MedicineDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
