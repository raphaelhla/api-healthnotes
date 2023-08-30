package com.ufcg.apihealthnotes.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "complexprocedures")
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

}
