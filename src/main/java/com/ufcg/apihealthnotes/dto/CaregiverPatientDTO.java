package com.ufcg.apihealthnotes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CaregiverPatientDTO {

	private PatientDTO patientDTO;
    private AtendimentoDTO atendimentoDTO;
}
