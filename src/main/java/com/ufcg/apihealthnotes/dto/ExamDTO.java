package com.ufcg.apihealthnotes.dto;

public class ExamDTO {
    private String category;
    private String doctor;
    private String description;

    public ExamDTO(String category, String doctor, String description) {
        this.category = category;
        this.doctor = doctor;
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
