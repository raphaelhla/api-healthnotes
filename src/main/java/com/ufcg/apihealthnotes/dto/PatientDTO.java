package com.ufcg.apihealthnotes.dto;

import java.util.ArrayList;
import java.util.List;

import com.ufcg.apihealthnotes.entities.Comorbiditie;
import com.ufcg.apihealthnotes.entities.ComplexProcedure;


public class PatientDTO {
    private String cpf;
    private String name;
    private String age;
    private List<Comorbiditie> comorbidities;
    private List<ComplexProcedure> complexProcedures;

    public PatientDTO() {
    	this.comorbidities = new ArrayList<>();
        this.complexProcedures = new ArrayList<>();
    }
    
    public PatientDTO(String cpf, String name, String age, List<Comorbiditie> comorbidities, List<ComplexProcedure> complexProcedures) {
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
        this.comorbidities = new ArrayList<>();
        this.complexProcedures = new ArrayList<>();
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

    public List<Comorbiditie> getComorbidities() {
        return comorbidities;
    }

    public void setComorbidities(List<Comorbiditie> comorbidities) {
        this.comorbidities = comorbidities;
    }

    public List<ComplexProcedure> getComplexProcedures() {
        return complexProcedures;
    }

    public void setComplexProcedures(List<ComplexProcedure> complexProcedures) {
        this.complexProcedures = complexProcedures;
    }

	@Override
	public String toString() {
		return "PatientDTO [cpf=" + cpf + ", name=" + name + ", age=" + age + ", comorbidities=" + comorbidities
				+ ", complexProcedures=" + complexProcedures + "]";
	}
    
    
}