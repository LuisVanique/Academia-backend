package com.br.luisvanique.academia.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.br.luisvanique.academia.domain.aluno.Aluno;
import com.br.luisvanique.academia.domain.aluno.ConstantesAluno;
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
	
	
	public MensalidadeService(List<MensalidadeStatusPagoValidator> mensalidadeValidators) {
		this.mensalidadeValidators = mensalidadeValidators;
	}

	
	@Scheduled(cron = "0 0 0 20 * ?")
	public void gerarMensalidade() {
		List<Aluno> alunos = alunoRepository.findAll();
		for(Aluno aluno : alunos) {
			if(aluno.getAtivo().equals(ConstantesAluno.alunoInativo)) {
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
		
		atualizarMensalidadesVencidas();
		inativarAlunosComMensalidadesVencidas();
	}

	public List<Mensalidade> findByFiltroStatus(Integer status) {
		if(status != null) {
			return mensalidadeRepository.findByFiltrosStatus(status);
		}
		return mensalidadeRepository.findAll();
	}
	
	private void atualizarMensalidadesVencidas() {
	    List<Mensalidade> mensalidades = mensalidadeRepository.findAll();
	    LocalDate hoje = LocalDate.now();
	    
	    for (Mensalidade mensalidade : mensalidades) {
	        if (mensalidade.getDataVencimento().isBefore(hoje) && mensalidade.getStatus() == StatusPagamento.PENDENTE.getCodigo()) {
	            mensalidade.setStatus(StatusPagamento.VENCIDA.getCodigo());
	            mensalidadeRepository.save(mensalidade);
	        }
	    }
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


	public List<Mensalidade> findAll() {
		return mensalidadeRepository.findAll();
	}
	
	private Integer contaMensalidadesVencidas(Aluno aluno) {
		List<Mensalidade> mensalidades = mensalidadeRepository.findByAluno(aluno);
		int count = 0;
		
		 for (Mensalidade mensalidade : mensalidades) {
		        if (mensalidade.getStatus() == StatusPagamento.VENCIDA.getCodigo()) {
		            count++;
		        }
		    }
		return count;
	}
	
	@Transactional
	private void inativarAlunosComMensalidadesVencidas() {
	    List<Aluno> alunos = alunoRepository.findAll();
	    
	    for (Aluno aluno : alunos) {
	        if (contaMensalidadesVencidas(aluno) >= 3) {
	            aluno.setAtivo(ConstantesAluno.alunoInativo);
	            alunoRepository.save(aluno);
	        }
	    }
	}
	
	
}
