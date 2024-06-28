package com.br.luisvanique.academia.domain.mensalidade.exception;

public class StatusDePagamentoInvalidoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public StatusDePagamentoInvalidoException(String msg) {
		super(msg);
	}

}
