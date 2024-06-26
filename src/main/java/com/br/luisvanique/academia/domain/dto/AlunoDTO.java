package com.br.luisvanique.academia.domain.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import com.br.luisvanique.academia.domain.Aluno;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record AlunoDTO(
		
		Long id,
		@NotNull(message = "Campo NOME obrigatorio!")
		String nome, 
		
		@Valid
		EnderecoDTO endereco,
		
		@CPF
		@NotNull(message = "Campo CPF obrigatorio!")
		String cpf,
		
		@NotNull(message = "Campo TELEFONE obrigatorio!")
		String telefone,
		
		LocalDate dataCriacao
		) {
	
	public AlunoDTO(Aluno aluno) {
		this(aluno.getId(), aluno.getNome(), new EnderecoDTO(aluno.getEndereco()),
				aluno.getCpf(), aluno.getTelefone(), aluno.getDataCriacao());
	}


}
