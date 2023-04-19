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
    @ElementCollection
    private List<String> medicines;
    @ElementCollection
    private List<String> vaccines;
    @OneToMany(mappedBy = "patient")
    private List<Surgery> surgeries;
    @ManyToOne
    @JoinColumn(name="caregiverId")
    private Caregiver caregiver;
    @OneToMany(mappedBy = "patient")
    private List<Exam> exams;

    public Patient(String cpf, String name, String birthday) {
        this.cpf = cpf;
        this.name = name;
        this.birthday = birthday;
        this.medicines = new ArrayList<>();
        this.vaccines = new ArrayList<>();
        this.surgeries = new ArrayList<>();
        this.exams = new ArrayList<>();
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

    public List<String> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<String> medicines) {
        this.medicines = medicines;
    }

    public List<String> getVaccines() {
        return vaccines;
    }

    public void setVaccines(List<String> vaccines) {
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
