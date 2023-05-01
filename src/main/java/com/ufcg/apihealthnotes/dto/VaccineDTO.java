package com.ufcg.apihealthnotes.dto;

import com.ufcg.apihealthnotes.entities.Patient;

public class VaccineDTO {

    private Patient patient;
    private String name;
    private String description;

    public VaccineDTO(Patient patient, String name, String description) {
        this.patient = patient;
        this.name = name;
        this.description = description;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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
