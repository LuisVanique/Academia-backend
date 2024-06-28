package com.br.luisvanique.academia.domain.aluno.dto;

import java.time.LocalDate;

import com.br.luisvanique.academia.domain.aluno.Aluno;

public record AlunoDTO(
		
		Long id,

		String nome, 
	
		EnderecoDTO endereco,
		
		String cpf,
		
		String telefone,
		
		LocalDate dataCriacao,
		
		String ativo
		) {
	
	public AlunoDTO(Aluno aluno) {
		this(aluno.getId(), aluno.getNome(), new EnderecoDTO(aluno.getEndereco()),
				aluno.getCpf(), aluno.getTelefone(), aluno.getDataCriacao(), aluno.getAtivo());
	}


}