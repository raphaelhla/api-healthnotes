package com.ufcg.apihealthnotes.dto;

public record DataRegisterDTO(String login, String password, String confirmPassword, String cpf, String name, String surname) {
}
