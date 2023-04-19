package com.ufcg.apihealthnotes.dto;

import com.ufcg.apihealthnotes.entities.Patient;
import com.ufcg.apihealthnotes.entities.Surgery;

import javax.persistence.*;
import java.util.Objects;

public class SurgeryDTO {
    private String doctor;
    private String cause;

    public SurgeryDTO(String doctor, String cause) {
        this.doctor = doctor;
        this.cause = cause;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
