package com.br.luisvanique.academia.domain.aluno.dto;

import java.time.LocalDate;

import com.br.luisvanique.academia.domain.aluno.Aluno;

public record AlunoDTO(
		
		Long id,

		String nome, 
	
		EnderecoDTO endereco,
		
		LocalDate dataNascimento,
		
		String telefone,
		
		LocalDate dataCriacao,
		
		String ativo
		) {
	
	public AlunoDTO(Aluno aluno) {
		this(aluno.getId(), aluno.getNome(), new EnderecoDTO(aluno.getEndereco()),
				aluno.getDataNascimento(), aluno.getTelefone(), aluno.getDataCriacao(), aluno.getAtivo());
	}


}