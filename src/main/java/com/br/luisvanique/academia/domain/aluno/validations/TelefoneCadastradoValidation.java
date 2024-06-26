package com.br.luisvanique.academia.domain.aluno.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.luisvanique.academia.domain.aluno.dto.CreateAlunoDTO;
import com.br.luisvanique.academia.domain.aluno.exception.TelefoneJaRegistradoException;
import com.br.luisvanique.academia.repository.AlunoRepository;

@Component
public class TelefoneCadastradoValidation implements ICreateUserValidator{

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Override
	public void validator(CreateAlunoDTO dto) {
		if(alunoRepository.existsByTelefone(dto.telefone())) {
			throw new TelefoneJaRegistradoException("Telefone já Cadastrado no sistema!");
		}
	}

}
