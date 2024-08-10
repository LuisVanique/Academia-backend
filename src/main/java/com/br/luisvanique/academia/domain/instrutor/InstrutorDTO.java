package com.br.luisvanique.academia.domain.instrutor;

public record InstrutorDTO(Long id, String email, String nome) {

	public InstrutorDTO(Instrutor instrutor) {
		this(instrutor.getId(), instrutor.getEmail(), instrutor.getNome());
	}

}
