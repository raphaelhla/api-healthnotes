package com.ufcg.apihealthnotes.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "caregiver_patient")
public class CaregiverPatient {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "caregiver_id")
    private Caregiver caregiver;

    private Double monthlyCost;

	public CaregiverPatient(Patient patient, Caregiver caregiver) {
		this.patient = patient;
		this.caregiver = caregiver;
		this.monthlyCost = 0.0;
	}

	public CaregiverPatient(Patient patient, Caregiver caregiver, Double monthlyCost) {
		this.patient = patient;
		this.caregiver = caregiver;
		this.monthlyCost = monthlyCost;
	}

	@Override
	public int hashCode() {
		return Objects.hash(caregiver, patient);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CaregiverPatient other = (CaregiverPatient) obj;
		return Objects.equals(caregiver, other.caregiver) && Objects.equals(patient, other.patient);
	}
	
}
