package com.br.luisvanique.academia.domain.aluno.dto;

import org.hibernate.validator.constraints.br.CPF;

import com.br.luisvanique.academia.domain.aluno.Aluno;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAlunoDTO(
		
		@NotNull(message = "Campo NOME obrigatorio!")
		@NotBlank(message = "Campo NOME nao pode estar vazio")
		String nome, 
		
		@Valid
		EnderecoDTO endereco,
		
		@CPF
		@NotNull(message = "Campo CPF obrigatorio!")
		@NotBlank(message = "Campo CPF nao pode estar vazio")
		String cpf,
		
		@NotNull(message = "Campo TELEFONE obrigatorio!")
		@NotBlank(message = "Campo TELEFONE nao pode estar vazio")
		String telefone
		) {
	
	public CreateAlunoDTO(Aluno aluno) {
		this(aluno.getNome(), new EnderecoDTO(aluno.getEndereco()),
				aluno.getCpf(), aluno.getTelefone());
	}


}
