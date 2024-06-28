package com.br.luisvanique.academia.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.luisvanique.academia.domain.aluno.Aluno;
import com.br.luisvanique.academia.domain.aluno.dto.CreateAlunoDTO;
import com.br.luisvanique.academia.domain.aluno.dto.UpdateAlunoDTO;
import com.br.luisvanique.academia.domain.aluno.exception.ObjectNotFoundException;
import com.br.luisvanique.academia.domain.aluno.validations.UserValidator;
import com.br.luisvanique.academia.repository.AlunoRepository;

import jakarta.validation.Valid;

@Service
public class AlunoService {
	@Autowired
	private AlunoRepository alunoRepository;
	
	private final List<UserValidator> userValidator;
	
	AlunoService(List<UserValidator> createUserValidator){
		this.userValidator = createUserValidator;
	}
	
	public List<Aluno> findAll() {
		return alunoRepository.findAll();
	}
	
	public Aluno findById(Long id) {
		return alunoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Aluno não encontrado"));
	}

	public Aluno create(@Valid CreateAlunoDTO dto) {
		userValidator.forEach(validator -> validator.validator(dto));
		Aluno aluno = new Aluno(dto);
		return alunoRepository.save(aluno);
	}

	public void update(Long id, @Valid UpdateAlunoDTO dto) {
		userValidator.forEach(validator -> validator.validator(dto, id));
		Aluno alunoAtual =
				alunoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Aluno não encontrado"));
		BeanUtils.copyProperties(dto, alunoAtual, "id");
		alunoRepository.save(alunoAtual);
	}

	public void delete(Long id) {
		Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Aluno não encontrado"));
		aluno.setAtivo("N");
		alunoRepository.save(aluno);
	}

	
}
