package com.br.luisvanique.academia.domain.aluno.exception;

public class TelefoneJaRegistradoException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public TelefoneJaRegistradoException(String message) {
		super(message);
	}
}
