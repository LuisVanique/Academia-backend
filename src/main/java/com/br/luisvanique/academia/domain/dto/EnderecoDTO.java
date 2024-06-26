package com.br.luisvanique.academia.domain.dto;

import com.br.luisvanique.academia.domain.Endereco;

import jakarta.validation.constraints.NotNull;

public record EnderecoDTO(
		@NotNull(message = "Campo CEP obrigatorio!") 
		String cep,
		
		@NotNull(message = "Campo LOGRADOURO obrigatorio!") 
		String logradouro,
		
		@NotNull(message = "Campo BAIRRO obrigatorio!") 
		String bairro,
		
		@NotNull(message = "Campo ESTADO obrigatorio!") 
		String estado,
		
		@NotNull(message = "Campo UF obrigatorio!") 
		String uf) {

	public EnderecoDTO(Endereco endereco) {
		this(endereco.getCep(), endereco.getLogradouro(), endereco.getBairro(), endereco.getEstado(), endereco.getUf());
	}

	public Endereco toEndereco() {
		return new Endereco(cep, logradouro, bairro, estado, uf);
	}
}
