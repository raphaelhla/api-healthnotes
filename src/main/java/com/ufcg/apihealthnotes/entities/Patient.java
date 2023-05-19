package com.ufcg.apihealthnotes.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ufcg.apihealthnotes.dto.PatientDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_patient")
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

    public Patient() {
    }

    public Patient(String cpf, String name, String age, List<Medicine> medicines,
                   List<Vaccine> vaccines, List<Surgery> surgeries, List<Exam> exams, Caregiver caregiver) {
        this.cpf = cpf;
        this.name = name;
        this.age = age;
        this.medicines = medicines;
        this.vaccines = vaccines;
        this.surgeries = surgeries;
        this.exams = exams;
        this.caregiver = caregiver;
    }

    public Patient(String cpf, String name, String age, Caregiver caregiver) {
        this.cpf = cpf;
        this.name = name;
        this.age = age;
        this.caregiver = caregiver;
        this.medicines = new ArrayList<>();
        this.vaccines = new ArrayList<>();
        this.surgeries = new ArrayList<>();
        this.exams = new ArrayList<>();
    }

    public Patient(PatientDTO patientDTO, String cpf) {
    }

    public Caregiver getCaregiver() {
        return caregiver;
    }

    public void setCaregiver(Caregiver caregiver) {
        this.caregiver = caregiver;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    public List<Vaccine> getVaccines() {
        return vaccines;
    }

    public void setVaccines(List<Vaccine> vaccines) {
        this.vaccines = vaccines;
    }

    public List<Surgery> getSurgeries() {
        return surgeries;
    }

    public void setSurgeries(List<Surgery> surgeries) {
        this.surgeries = surgeries;
    }

    public void updateFromDTO(PatientDTO patientDTO) {

        this.name = patientDTO.getName();
        this.age = patientDTO.getAge();

        List<Exam> exams = patientDTO.getExams().stream().map(e -> new Exam(this, e.getCategory(), e.getDoctor(), e.getDescription()))
                .collect(Collectors.toList());

        List<Surgery> surgeries = patientDTO.getSurgeries().stream().map(s -> new Surgery(this, s.getDoctor(), s.getCause()))
                .collect(Collectors.toList());

        List<Medicine> medicines = patientDTO.getMedicines().stream().map(m -> new Medicine(this, m.getName(), m.getDescription()))
                .collect(Collectors.toList());

        List<Vaccine> vaccines = patientDTO.getVaccines().stream().map(v -> new Vaccine(this, v.getName(), v.getDescription()))
                .collect(Collectors.toList());

        this.exams = exams;
        this.medicines = medicines;
        this.surgeries = surgeries;
        this.vaccines = vaccines;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(cpf, patient.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
