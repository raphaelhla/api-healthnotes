package com.ufcg.apihealthnotes.dto;

import com.ufcg.apihealthnotes.entities.Comorbidities;
import com.ufcg.apihealthnotes.entities.ComplexProcedures;

import java.util.List;

public class PatientDTO {
    private String cpf;
    private String name;
    private String age;
    private List<Comorbidities> comorbidities;
    private List<ComplexProcedures> complexProcedures;

    public PatientDTO(String cpf, String name, String age, List<Comorbidities> comorbidities, List<ComplexProcedures> complexProcedures) {
        this.cpf = cpf;
        this.name = name;
        this.age = age;
        this.comorbidities = comorbidities;
        this.complexProcedures = complexProcedures;
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

    public List<Comorbidities> getComorbidities() {
        return comorbidities;
    }

    public void setComorbidities(List<Comorbidities> comorbidities) {
        this.comorbidities = comorbidities;
    }

    public List<ComplexProcedures> getComplexProcedures() {
        return complexProcedures;
    }

    public void setComplexProcedures(List<ComplexProcedures> complexProcedures) {
        this.complexProcedures = complexProcedures;
    }
}