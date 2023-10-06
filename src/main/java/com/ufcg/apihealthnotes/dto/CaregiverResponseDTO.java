package com.ufcg.apihealthnotes.dto;

import com.ufcg.apihealthnotes.entities.caregiver.Caregiver;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CaregiverResponseDTO {
	
	private String cpf; 
	private String name;
	private String lastname;
	private String email;

	public CaregiverResponseDTO(Caregiver caregiver) {
		this(caregiver.getCpf(), caregiver.getName(), caregiver.getLastname(), caregiver.getEmail());
	}

}
