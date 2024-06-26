package com.br.luisvanique.academia.domain.aluno.validations;

import com.br.luisvanique.academia.domain.aluno.dto.CreateAlunoDTO;

public interface ICreateUserValidator {
	
	void validator(CreateAlunoDTO dto);
	
}
