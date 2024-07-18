package com.br.luisvanique.academia.domain.aluno.dto;

import java.time.LocalDate;

import com.br.luisvanique.academia.domain.aluno.Aluno;

public record UpdateAlunoDTO(
		String nome,
		EnderecoDTO endereco,
		LocalDate dataNascimento,
		String telefone) {
	
	
	public UpdateAlunoDTO(Aluno aluno) {
		this(aluno.getNome(), new EnderecoDTO(aluno.getEndereco()), aluno.getDataNascimento(), aluno.getTelefone());
	}
}
