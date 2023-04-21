package com.ufcg.apihealthnotes.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "tb_surgery")
public class Surgery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name="patientId")
    @JsonIgnore
    private Patient patient;
    private String doctor;
    private String cause;

    public Surgery(){}

    public Surgery(Patient patient, String doctor, String cause) {
        this.patient = patient;
        this.doctor = doctor;
        this.cause = cause;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Surgery surgery = (Surgery) o;
        return Objects.equals(id, surgery.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
