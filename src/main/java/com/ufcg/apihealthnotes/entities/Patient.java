package com.ufcg.apihealthnotes.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private List<Medicine> medicines;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Vaccine> vaccines;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Surgery> surgeries;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Exam> exams;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Calendar> calendar;

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
}
