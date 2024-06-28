package com.br.luisvanique.academia.domain.aluno.validations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.luisvanique.academia.domain.aluno.Aluno;
import com.br.luisvanique.academia.domain.aluno.dto.CreateAlunoDTO;
import com.br.luisvanique.academia.domain.aluno.dto.UpdateAlunoDTO;
import com.br.luisvanique.academia.domain.aluno.exception.TelefoneJaRegistradoException;
import com.br.luisvanique.academia.repository.AlunoRepository;

@Component
public class TelefoneCadastradoValidation implements UserValidator{

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Override
	public void validator(CreateAlunoDTO dto) {
		if(alunoRepository.existsByTelefone(dto.telefone())) {
			throw new TelefoneJaRegistradoException("Telefone já Cadastrado no sistema!");
		}
	}

	@Override
	public void validator(UpdateAlunoDTO dto, Long id) {
		Optional<Aluno> aluno = alunoRepository.findByTelefone(dto.telefone());
		if(alunoRepository.existsByTelefone(dto.telefone()) && !aluno.get().getId().equals(id)) {
			throw new TelefoneJaRegistradoException("Telefone já Cadastrado no sistema!");
		}
	}

}
