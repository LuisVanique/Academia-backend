package com.br.luisvanique.academia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.luisvanique.academia.domain.aluno.Aluno;
import com.br.luisvanique.academia.domain.aluno.dto.CreateAlunoDTO;
import com.br.luisvanique.academia.domain.aluno.exception.CpfJaRegistradoException;
import com.br.luisvanique.academia.domain.aluno.exception.TelefoneJaRegistradoException;
import com.br.luisvanique.academia.domain.aluno.validations.CPFCadastradoValidation;
import com.br.luisvanique.academia.domain.aluno.validations.ICreateUserValidator;
import com.br.luisvanique.academia.domain.aluno.validations.TelefoneCadastradoValidation;
import com.br.luisvanique.academia.repository.AlunoRepository;

import jakarta.validation.Valid;

@Service
public class AlunoService {
	@Autowired
	private AlunoRepository alunoRepository;
	
	private final List<ICreateUserValidator> createUserValidator;
	
	AlunoService(List<ICreateUserValidator> createUserValidator){
		this.createUserValidator = createUserValidator;
	}
	
	public List<Aluno> findAll() {
		return alunoRepository.findAll();
	}

	public Aluno create(@Valid CreateAlunoDTO dto) {
		createUserValidator.forEach(validator -> validator.validator(dto));
		Aluno aluno = new Aluno(dto);
		return alunoRepository.save(aluno);
	}
}
