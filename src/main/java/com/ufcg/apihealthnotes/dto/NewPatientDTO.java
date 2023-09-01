package com.ufcg.apihealthnotes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewPatientDTO {

	private PatientDTO patientDTO;
    private CaregiverPatientDTO caregiverPatientDTO;
}
