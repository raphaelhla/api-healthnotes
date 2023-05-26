package com.ufcg.apihealthnotes.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tb_patient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "cpf")
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

    @ManyToOne
    @JoinColumn(name = "caregiverId")
    @JsonIgnore
    private Caregiver caregiver;

    public Patient(String cpf, String name, String age, Caregiver caregiver) {
        this.cpf = cpf;
        this.name = name;
        this.age = age;
        this.caregiver = caregiver;
    }

}

//    public void updateFromDTO(PatientDTO patientDTO) {
//
//        this.name = patientDTO.getName();
//        this.age = patientDTO.getAge();
//
//        List<Exam> exams = patientDTO.getExams().stream().map(e -> new Exam(this, e.getCategory(), e.getDoctor(), e.getDescription()))
//                .collect(Collectors.toList());
//
//        List<Surgery> surgeries = patientDTO.getSurgeries().stream().map(s -> new Surgery(this, s.getDoctor(), s.getCause()))
//                .collect(Collectors.toList());
//
//        List<Medicine> medicines = patientDTO.getMedicines().stream().map(m -> new Medicine(this, m.getName(), m.getDescription()))
//                .collect(Collectors.toList());
//
//        List<Vaccine> vaccines = patientDTO.getVaccines().stream().map(v -> new Vaccine(this, v.getName(), v.getDescription()))
//                .collect(Collectors.toList());
//
//        this.exams = exams;
//        this.medicines = medicines;
//        this.surgeries = surgeries;
//        this.vaccines = vaccines;
//
//    }
//}
