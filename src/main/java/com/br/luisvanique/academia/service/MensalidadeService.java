package com.br.luisvanique.academia.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.br.luisvanique.academia.domain.aluno.Aluno;
import com.br.luisvanique.academia.domain.enums.StatusPagamento;
import com.br.luisvanique.academia.domain.mensalidade.Mensalidade;
import com.br.luisvanique.academia.repository.AlunoRepository;
import com.br.luisvanique.academia.repository.MensalidadeRepository;

@Service
public class MensalidadeService {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private MensalidadeRepository mensalidadeRepository;
	
	@Scheduled(cron = "0 * * * * ?")
	public void gerarMensalidade() {
		List<Aluno> alunos = alunoRepository.findAll();
		for(Aluno aluno : alunos) {
			Mensalidade mensalidade = new Mensalidade();
			// Obter a data atual e adicionar um mês
            LocalDate dataAtual = LocalDate.now();
            LocalDate dataVencimento = dataAtual.plusMonths(1).withDayOfMonth(20);
			mensalidade.setAluno(aluno);
			mensalidade.setDataVencimento(dataVencimento);
			mensalidade.setValor(50.00);
			mensalidade.setStatus(StatusPagamento.PENDENTE.getCodigo());
			mensalidadeRepository.save(mensalidade);
		}
	}

	public List<Mensalidade> findByFiltroStatus(Integer status) {
		return mensalidadeRepository.findByFiltrosStatus(status);
	}
	
	
}
