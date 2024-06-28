package com.br.luisvanique.academia.domain.aluno.validations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.luisvanique.academia.domain.aluno.Aluno;
import com.br.luisvanique.academia.domain.aluno.dto.CreateAlunoDTO;
import com.br.luisvanique.academia.domain.aluno.dto.UpdateAlunoDTO;
import com.br.luisvanique.academia.domain.aluno.exception.CpfJaRegistradoException;
import com.br.luisvanique.academia.repository.AlunoRepository;

@Component
public class CPFCadastradoValidation implements UserValidator{

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Override
	public void validator(CreateAlunoDTO dto) {
		if(alunoRepository.existsByCpf(dto.cpf())) {
			throw new CpfJaRegistradoException("O CPF Já está cadastrado no sistema!");
		}
	}

	@Override
	public void validator(UpdateAlunoDTO dto, Long id) {
		Optional<Aluno> aluno = alunoRepository.findByCpf(dto.cpf());
		if(alunoRepository.existsByCpf(dto.cpf()) && !aluno.get().getId().equals(id)) {
			throw new CpfJaRegistradoException("O CPF Já está cadastrado no sistema!");
		}
	}
}
