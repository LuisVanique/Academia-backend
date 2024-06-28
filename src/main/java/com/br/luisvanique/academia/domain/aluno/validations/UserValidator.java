package com.br.luisvanique.academia.domain.aluno.validations;

import com.br.luisvanique.academia.domain.aluno.dto.CreateAlunoDTO;
import com.br.luisvanique.academia.domain.aluno.dto.UpdateAlunoDTO;

public interface UserValidator {
	
	void validator(CreateAlunoDTO dto);
	
	void validator(UpdateAlunoDTO dto, Long id);
	
}
