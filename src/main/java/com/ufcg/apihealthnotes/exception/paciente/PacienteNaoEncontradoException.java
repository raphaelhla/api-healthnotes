package com.ufcg.apihealthnotes.exception.paciente;

import com.ufcg.apihealthnotes.exception.BusinessException;

public class PacienteNaoEncontradoException extends BusinessException {

	public PacienteNaoEncontradoException() {
        super("Paciente não encontrado.");
    }
}
