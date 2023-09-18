package com.ufcg.apihealthnotes.exception;

public class BusinessException extends RuntimeException {
    public BusinessException() {
        super("Erro inesperado no Health Notes!");
    }

    public BusinessException(String message) {
        super(message);
    }
}