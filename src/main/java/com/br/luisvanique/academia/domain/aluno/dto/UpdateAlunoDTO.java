package com.br.luisvanique.academia.domain.aluno.dto;

import com.br.luisvanique.academia.domain.aluno.Aluno;

public record UpdateAlunoDTO(
		String nome,
		EnderecoDTO endereco,
		String cpf,
		String telefone) {
	
	
	public UpdateAlunoDTO(Aluno aluno) {
		this(aluno.getNome(), new EnderecoDTO(aluno.getEndereco()), aluno.getCpf(), aluno.getTelefone());
	}
}
