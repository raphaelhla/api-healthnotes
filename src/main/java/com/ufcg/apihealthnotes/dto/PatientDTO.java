package com.ufcg.apihealthnotes.dto;

import java.util.List;

public class PatientDTO {

    private String caregiverCpf;
    private String cpf;
    private String name;
    private String age;

    public PatientDTO(String caregiverCpf, String cpf, String name, String age) {

        this.caregiverCpf = caregiverCpf;
        this.cpf = cpf;
        this.name = name;
        this.age = age;
    }

    public String getCaregiverCpf() {
        return caregiverCpf;
    }

    public void setCaregiverCpf(String caregiverCpf) {
        this.caregiverCpf = caregiverCpf;
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