package com.ufcg.apihealthnotes.dto;

import com.ufcg.apihealthnotes.entities.Caregiver;

import java.util.List;
import java.util.Set;

public class PatientDTO {

    //private String caregiverCpf;
    private String cpf;
    private String name;
    private String age;
    //private Set<Caregiver> caregivers;

    public PatientDTO(String cpf, String name, String age) {
        this.cpf = cpf;
        this.name = name;
        this.age = age;
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
}