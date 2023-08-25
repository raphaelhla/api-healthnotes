package com.ufcg.apihealthnotes.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ufcg.apihealthnotes.dto.PatientDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_patient")
@Getter
@Setter
@EqualsAndHashCode(of = "cpf")
@AllArgsConstructor
@NoArgsConstructor
public class Patient implements Serializable{

    private static final long serialVersionUID = 1L;

	@Id
    private String cpf;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 3)
    private String age;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Comorbiditie> comorbidities;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<ComplexProcedure> complexProcedures;

    
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<LocalDate, Calendar> calendar;
    
//    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Calendar> calendar;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "patient_caregiver",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "caregiver_id"))
    private Set<Caregiver> caregivers = new HashSet<>();

    public Patient(PatientDTO patientDTO) {
	   List<Comorbiditie> comorbidities = Arrays.stream(patientDTO.getComorbidities().split(","))
	            .map(description -> new Comorbiditie(this, description.trim()))
	            .collect(Collectors.toList());

	    List<ComplexProcedure> complexProcedures = Arrays.stream(patientDTO.getComplexProcedures().split(","))
	            .map(description -> new ComplexProcedure(this, description.trim()))
	            .collect(Collectors.toList());
        
        this.cpf = patientDTO.getCpf();
        this.name = patientDTO.getName();
        this.age = patientDTO.getAge();
		this.calendar = new HashMap<>();
        this.comorbidities = comorbidities;
        this.complexProcedures = complexProcedures;
//		updateFromDTO(patientDTO);
    }

    public void updateFromDTO(PatientDTO patientDTO) {
//        List<Comorbiditie> comorbidities = patientDTO.getComorbidities().stream()
//                .map(e -> new Comorbiditie(this, e.getDescription()))
//                .collect(Collectors.toList());
//
//        List<ComplexProcedure> complexProcedures = patientDTO.getComplexProcedures().stream()
//                .map(s -> new ComplexProcedure(this, s.getDescription()))
//                .collect(Collectors.toList());

        this.name = patientDTO.getName();
        this.age = patientDTO.getAge();
//        this.comorbidities = patientDTO.getComorbidities();
//        this.complexProcedures = patientDTO.getComplexProcedures();
    }

	public void addComplexProcedure(ComplexProcedure complexProcedure) {
		this.complexProcedures.add(complexProcedure);
	}

	public void addComorbiditie(Comorbiditie comorbiditie) {
		this.comorbidities.add(comorbiditie);
	}

	public void addCaregiver(Caregiver caregiver) {
		this.caregivers.add(caregiver);
	}

}
