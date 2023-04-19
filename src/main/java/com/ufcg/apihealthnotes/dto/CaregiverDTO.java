package com.ufcg.apihealthnotes.dto;

import com.ufcg.apihealthnotes.entities.Caregiver;
import com.ufcg.apihealthnotes.entities.Patient;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CaregiverDTO {

    @Id
    private String cpf;

    private String name;

    public CaregiverDTO(){}

    public CaregiverDTO(String cpf, String name) {
        this.cpf = cpf;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaregiverDTO that = (CaregiverDTO) o;
        return Objects.equals(cpf, that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}

