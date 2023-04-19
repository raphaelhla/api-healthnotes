package com.ufcg.apihealthnotes.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_patient")
public class Patient {

    @Id
    private String cpf;
    private String name;
    private String birthday;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Medicine> medicines;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Vaccine> vaccines;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Surgery> surgeries;
    @ManyToOne
    @JoinColumn(name="caregiverId")
    private Caregiver caregiver;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Exam> exams;

    public Patient(){}

    public Patient(String cpf, String name, String birthday, List<Medicine> medicines,
                   List<Vaccine> vaccines, List<Surgery> surgeries, Caregiver caregiver, List<Exam> exams) {
        this.cpf = cpf;
        this.name = name;
        this.birthday = birthday;
        this.medicines = medicines;
        this.vaccines = vaccines;
        this.surgeries = surgeries;
        this.caregiver = caregiver;
        this.exams = exams;
    }

    public Patient(String cpf, String name, String birthday, Caregiver caregiver) {
        this.cpf = cpf;
        this.name = name;
        this.birthday = birthday;
        this.caregiver = caregiver;
        this.medicines = new ArrayList<>();
        this.vaccines = new ArrayList<>();
        this.surgeries = new ArrayList<>();
        this.exams = new ArrayList<>();
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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
