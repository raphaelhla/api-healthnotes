package com.ufcg.apihealthnotes.exception.cuidador;

import com.ufcg.apihealthnotes.exception.BusinessException;

public class CuidadorNaoEncontradoException extends BusinessException {

	public CuidadorNaoEncontradoException() {
        super("Cuidador não encontrado.");
    }
}
