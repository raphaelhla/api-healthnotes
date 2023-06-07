package com.ufcg.apihealthnotes.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ufcg.apihealthnotes.dto.PatientDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_patient")
@Getter
@Setter
@EqualsAndHashCode(of = "cpf")
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    private String cpf;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 3)
    private String age;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Comorbidities> comorbidities;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<ComplexProcedures> complexProcedures;



    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "patient_caregiver",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "caregiver_id"))
    private Set<Caregiver> caregivers = new HashSet<>();

    public Patient(String cpf, String name, String age) {
        this.cpf = cpf;
        this.name = name;
        this.age = age;
    }

    public void updateFromDTO(PatientDTO patientDTO) {

        this.name = patientDTO.getName();

        List<Comorbidities> comorbidities = patientDTO.getComorbidities().stream()
                .map(e -> new Comorbidities(this, e.getDescription()))
                .collect(Collectors.toList());

        List<ComplexProcedures> complexProcedures = patientDTO.getComplexProcedures().stream()
                .map(s -> new ComplexProcedures(this, s.getDescription()))
                .collect(Collectors.toList());

        this.comorbidities = comorbidities;
        this.complexProcedures = complexProcedures;

    }

}
