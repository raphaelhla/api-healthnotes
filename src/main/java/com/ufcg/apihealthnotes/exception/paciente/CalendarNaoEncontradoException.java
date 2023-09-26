package com.ufcg.apihealthnotes.exception.paciente;

import com.ufcg.apihealthnotes.exception.BusinessException;

public class CalendarNaoEncontradoException extends BusinessException {

	public CalendarNaoEncontradoException() {
        super("Calendário não encontrado.");
    }
}

