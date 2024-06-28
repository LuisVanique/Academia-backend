package com.br.luisvanique.academia.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.br.luisvanique.academia.domain.aluno.Aluno;
import com.br.luisvanique.academia.domain.enums.StatusPagamento;
import com.br.luisvanique.academia.domain.mensalidade.Mensalidade;
import com.br.luisvanique.academia.domain.mensalidade.dto.AprovarPagamentoDTO;
import com.br.luisvanique.academia.domain.mensalidade.validations.MensalidadeStatusPagoValidator;
import com.br.luisvanique.academia.repository.AlunoRepository;
import com.br.luisvanique.academia.repository.MensalidadeRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class MensalidadeService {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private MensalidadeRepository mensalidadeRepository;
	
	private List<MensalidadeStatusPagoValidator> mensalidadeValidators;
	
	private final String alunoInativo = "N";
	
	public MensalidadeService(List<MensalidadeStatusPagoValidator> mensalidadeValidators) {
		this.mensalidadeValidators = mensalidadeValidators;
	}

	
	@Scheduled(cron = "0 * * * * ?")
	public void gerarMensalidade() {
		List<Aluno> alunos = alunoRepository.findAll();
		for(Aluno aluno : alunos) {
			if(aluno.getAtivo().equals(alunoInativo)) {
				continue;
			}
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
	
	public void aprovarPagamento(Long id, @Valid AprovarPagamentoDTO status) {
		mensalidadeValidators.forEach(mensalidade -> mensalidade.validate(status.status()));
		Mensalidade mensalidadeNova = mensalidadeRepository.findById(id).orElseThrow();
		BeanUtils.copyProperties(status, mensalidadeNova);
		mensalidadeRepository.save(mensalidadeNova);
	}

	@Transactional
	public List<Mensalidade> mensalidadeCriacaoUsuario(Aluno aluno) {
		Mensalidade salvarMensalidade = new Mensalidade();
		LocalDate dataAtual = LocalDate.now();
        LocalDate dataVencimento = dataAtual.plusMonths(1).withDayOfMonth(20);
        salvarMensalidade.setValor(50.00);
        salvarMensalidade.setStatus(StatusPagamento.PENDENTE.getCodigo());
        salvarMensalidade.setDataVencimento(dataVencimento);
        salvarMensalidade.setAluno(aluno);
        
        List<Mensalidade> mensalidades = new ArrayList<>();
        mensalidades.add(salvarMensalidade);
        mensalidadeRepository.saveAll(mensalidades);
		return mensalidades;
	}
	
	
}
