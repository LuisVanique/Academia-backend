package com.br.luisvanique.academia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.luisvanique.academia.controller.dto.CreateInstrutorDTO;
import com.br.luisvanique.academia.controller.exception.EmailJaCadastradoException;
import com.br.luisvanique.academia.domain.instrutor.Instrutor;
import com.br.luisvanique.academia.repository.InstrutorRepository;

@Service
public class InstrutorService {
	
	@Autowired
	public InstrutorRepository repository;
	
	@Autowired
	BCryptPasswordEncoder encoder;

	public Instrutor create(CreateInstrutorDTO dto) {
		if(repository.existsByEmail(dto.email())) {
			throw new EmailJaCadastradoException("Email já cadastrado!");
		}
		
		Instrutor instrutor = new Instrutor(dto);
		instrutor.setSenha(encoder.encode(dto.senha()));
		repository.save(instrutor);
		return instrutor;
	}

}
