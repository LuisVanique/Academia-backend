package com.br.luisvanique.academia.domain.mensalidade.dto;

import java.time.LocalDate;

import com.br.luisvanique.academia.domain.aluno.dto.AlunoDTO;
import com.br.luisvanique.academia.domain.enums.StatusPagamento;
import com.br.luisvanique.academia.domain.mensalidade.Mensalidade;

public record MensalidadeDTO(
		AlunoDTO aluno,
		
		LocalDate dataVencimento,
		
		Double valor,
		
		Integer status) {

	public MensalidadeDTO(Mensalidade mensalidade) {
		this(new AlunoDTO(mensalidade.getAluno()), mensalidade.getDataVencimento(), mensalidade.getValor(),
				mensalidade.getStatus());
	}
}
