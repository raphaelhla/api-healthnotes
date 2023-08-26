package com.ufcg.apihealthnotes.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ufcg.apihealthnotes.entities.Calendar;
import com.ufcg.apihealthnotes.entities.Caregiver;
import com.ufcg.apihealthnotes.entities.Comorbiditie;
import com.ufcg.apihealthnotes.entities.ComplexProcedure;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDTO {
    private String cpf;
    private String name;
    private String age;
    private String comorbidities;
    private String complexProcedures;
    private String profilePhoto;

    public PatientDTO() {

    }
    
    public PatientDTO(String cpf, String name, String age, String comorbidities, String complexProcedures, String profilePhoto) {
        this.cpf = cpf;
        this.name = name;
        this.age = age;
        this.comorbidities = comorbidities;
        this.complexProcedures = complexProcedures;
        this.profilePhoto = profilePhoto;
    }
    
    public PatientDTO(String cpf, String name, String age) {
        this.cpf = cpf;
        this.name = name;
        this.age = age;
    }
}