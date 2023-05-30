package com.ufcg.apihealthnotes.dto;

public record CaregiverRegisterDTO(String email, String password, String confirmPassword, String cpf, String name, String lastname) {
}
