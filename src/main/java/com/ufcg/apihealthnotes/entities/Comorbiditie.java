package com.ufcg.apihealthnotes.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comorbidities")
public class Comorbiditie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "patientId")
    @JsonIgnore
    private Patient patient;
    private String description;

    public Comorbiditie() {
    }

    public Comorbiditie(Patient patient, String description) {
        this.patient = patient;
        this.description = description;
    }

    public Comorbiditie(Patient patient) {
        this.patient = patient;
    }
}
