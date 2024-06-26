package com.br.luisvanique.academia.domain.aluno.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.luisvanique.academia.domain.aluno.dto.CreateAlunoDTO;
import com.br.luisvanique.academia.domain.aluno.exception.CpfJaRegistradoException;
import com.br.luisvanique.academia.repository.AlunoRepository;

@Component
public class CPFCadastradoValidation implements ICreateUserValidator{

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Override
	public void validator(CreateAlunoDTO dto) {
		if(alunoRepository.existsByCpf(dto.cpf())) {
			throw new CpfJaRegistradoException("O CPF Já está cadastrado no sistema!");
		}
	}

}
