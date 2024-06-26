package com.br.luisvanique.academia.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Endereco {
	@Column(name = "CEP")
	@NotNull
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
