package com.br.luisvanique.academia.domain.dto;

import java.time.LocalDate;

import com.br.luisvanique.academia.domain.Aluno;
import com.br.luisvanique.academia.domain.Endereco;

public record AlunoDTO(
		Long id,
		String nome, 
		Endereco endereco,
		String cpf,
		String telefone,
		LocalDate dataCriacao
		) {
	
	public AlunoDTO(Aluno aluno) {
		this(aluno.getId(), aluno.getNome(), aluno.getEndereco(),
				aluno.getCpf(), aluno.getTelefone(), aluno.getDataCriacao());
	}

}
