package com.br.luisvanique.academia.domain.aluno.dto;

import java.time.LocalDate;

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
		
		@NotNull(message = "Campo Data Nascimento é obrigatorio!")
		LocalDate dataNascimento,
		
		@NotNull(message = "Campo TELEFONE obrigatorio!")
		@NotBlank(message = "Campo TELEFONE nao pode estar vazio")
		String telefone
		) {
	
	public CreateAlunoDTO(Aluno aluno) {
		this(aluno.getNome(), new EnderecoDTO(aluno.getEndereco()),
				aluno.getDataNascimento(), aluno.getTelefone());
	}


}
