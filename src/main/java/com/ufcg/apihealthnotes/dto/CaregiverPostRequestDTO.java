package com.ufcg.apihealthnotes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class CaregiverPostRequestDTO {
		
		@JsonProperty("email")
		private String email;
		
		@JsonProperty("email")
		private String password;
		
		@JsonProperty("email")
		private String confirmPassword;
		
		@JsonProperty("email")
		private String cpf;

		@JsonProperty("email")
		private String name;
		
		@JsonProperty("email")
		private String lastname;
}
