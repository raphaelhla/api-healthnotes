package com.ufcg.apihealthnotes.dto;

public class PatientDTO {
    private String cpf;
    private String name;
    private String age;
    private String comorbidities;
    private String complexProcedures;

    public PatientDTO() {

    }
    
    public PatientDTO(String cpf, String name, String age, String comorbidities, String complexProcedures) {
        this.cpf = cpf;
        this.name = name;
        this.age = age;
        this.comorbidities = comorbidities;
        this.complexProcedures = complexProcedures;
    }
    
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

    public String getComorbidities() {
        return comorbidities;
    }

    public void setComorbidities(String comorbidities) {
        this.comorbidities = comorbidities;
    }

    public String getComplexProcedures() {
        return complexProcedures;
    }

    public void setComplexProcedures(String complexProcedures) {
        this.complexProcedures = complexProcedures;
    }
}