package com.br.luisvanique.academia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.luisvanique.academia.domain.Aluno;
import com.br.luisvanique.academia.domain.dto.AlunoDTO;
import com.br.luisvanique.academia.repository.AlunoRepository;

import jakarta.validation.Valid;

@Service
public class AlunoService {
	@Autowired
	private AlunoRepository alunoRepository;
	
	public List<Aluno> findAll() {
		return alunoRepository.findAll();
	}

	public Aluno create(@Valid AlunoDTO dto) {
		Aluno aluno = new Aluno(dto);
		return alunoRepository.save(aluno);
	}
}
