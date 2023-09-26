package com.ufcg.apihealthnotes.exception.paciente;

import com.ufcg.apihealthnotes.exception.BusinessException;

public class ChecklistItemNaoEncontradoException extends BusinessException {

	public ChecklistItemNaoEncontradoException(Long checklistItemId, String pacienteCpf) {
        super(String.format("Não existe nenhum ChecklistItem com o id %d associado ao paciente com o cpf %s",
				checklistItemId, pacienteCpf));
    }
}
