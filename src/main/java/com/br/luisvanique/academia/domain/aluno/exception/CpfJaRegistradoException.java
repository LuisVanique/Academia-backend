package com.br.luisvanique.academia.domain.aluno.exception;

public class CpfJaRegistradoException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public CpfJaRegistradoException(String message) {
		super(message);
	}

}
