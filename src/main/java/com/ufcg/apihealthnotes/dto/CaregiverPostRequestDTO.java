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
		
		@JsonProperty("password")
		private String password;
		
		@JsonProperty("confirmPassword")
		private String confirmPassword;
		
		@JsonProperty("cpf")
		private String cpf;

		@JsonProperty("name")
		private String name;
		
		@JsonProperty("lastname")
		private String lastname;
}
