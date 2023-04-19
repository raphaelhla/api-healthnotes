package com.ufcg.apihealthnotes.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_caregiver")
public class Caregiver {
    @Id
    private String cpf;
    private String name;
    private String password;

    @OneToMany(mappedBy="caregiver")
    private List<Patient> patients;

    public Caregiver(){}

    public Caregiver(String cpf, String name, String password) {
        this.cpf = cpf;
        this.name = name;
        this.password = password;
        this.patients = new ArrayList<>();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Caregiver caregiver = (Caregiver) o;
        return Objects.equals(cpf, caregiver.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
