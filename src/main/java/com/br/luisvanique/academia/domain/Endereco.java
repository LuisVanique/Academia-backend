package com.br.luisvanique.academia.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Endereco {
	@Column(name = "CEP")
	private String cep;
	
	@Column(name = "LOGRADOURO")
	private String logradouro;
	
	@Column(name = "BAIRRO")
	private String bairro;
	
	@Column(name = "ESTADO")
	private String estado;
	
	@Column(name = "UF")
	private String uf;
}
