package com.br.luisvanique.academia.domain.enums;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
public enum StatusPagamento {
	ANULADA(4, "ANULADA"),
	VENCIDA(3, "VENCIDA"), 
	PAGA(2, "PAGA"), 
	PENDENTE(1, "PENDENTE");
	
	@Id
	@Column(name = "ID")
	private Integer codigo;
	
	@Column(name = "STATUS_PAGAMENTO")
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
