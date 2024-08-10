package com.br.luisvanique.academia.controller.dto;

import com.br.luisvanique.academia.domain.instrutor.Instrutor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CreateInstrutorDTO(
		@Email
		@NotNull
		String email, 
		@NotNull
		String senha, 
		@NotNull
		String nome) {
	
	CreateInstrutorDTO(Instrutor instrutor){
		this(instrutor.getEmail(), instrutor.getSenha(), instrutor.getNome());
	}
}
