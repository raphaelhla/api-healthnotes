package com.ufcg.apihealthnotes.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_complexprocedures")
public class ComplexProcedure {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "patientId")
    @JsonIgnore
    private Patient patient;
    private String description;

    public ComplexProcedure() {
    }

    public ComplexProcedure(Patient patient) {
        this.patient = patient;
    }

    public ComplexProcedure(Patient patient, String description) {
        this.patient = patient;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
