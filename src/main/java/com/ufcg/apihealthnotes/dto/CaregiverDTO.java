package com.ufcg.apihealthnotes.dto;

import com.ufcg.apihealthnotes.entities.caregiver.Caregiver;

public record CaregiverDTO(String cpf, String name, String lastname, String email) {
	
	public CaregiverDTO(Caregiver caregiver) {
		this(caregiver.getCpf(), caregiver.getName(), caregiver.getLastname(), caregiver.getEmail());
	}

}
