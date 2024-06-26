package com.br.luisvanique.academia.domain.enums;

import lombok.Getter;

@Getter
public enum StatusPagamento {
	VENCIDA(0, "VENCIDA"), PAGA(1, "PAGA"), PENDENTE(2, "PENDENTE");
	
	private int codigo;
	private String descricao;
	
	private StatusPagamento(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
}
