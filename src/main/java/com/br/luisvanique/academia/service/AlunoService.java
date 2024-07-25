package com.br.luisvanique.academia.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.luisvanique.academia.domain.aluno.Aluno;
import com.br.luisvanique.academia.domain.aluno.dto.CreateAlunoDTO;
import com.br.luisvanique.academia.domain.aluno.dto.UpdateAlunoDTO;
import com.br.luisvanique.academia.domain.aluno.exception.ObjectNotFoundException;
import com.br.luisvanique.academia.domain.aluno.validations.UserValidator;
import com.br.luisvanique.academia.domain.enums.StatusPagamento;
import com.br.luisvanique.academia.domain.mensalidade.Mensalidade;
import com.br.luisvanique.academia.repository.AlunoRepository;
import com.br.luisvanique.academia.repository.MensalidadeRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class AlunoService {
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private MensalidadeService mensalidadeService;
	
	@Autowired
	private MensalidadeRepository mensalidadeRepository;
	
	private final List<UserValidator> userValidator;
	
	AlunoService(List<UserValidator> createUserValidator){
		this.userValidator = createUserValidator;
	}
	

	public Page<Aluno> findAll(Pageable pageable) {
		return alunoRepository.findAll(pageable);
	}
	
	public Aluno findById(Long id) {
		return alunoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Aluno não encontrado"));
	}

	@Transactional
	public Aluno create(@Valid CreateAlunoDTO dto) {
		userValidator.forEach(validator -> validator.validator(dto));
		Aluno aluno = new Aluno(dto);
		aluno = alunoRepository.save(aluno);
		aluno.setMensalidades(mensalidadeService.mensalidadeCriacaoUsuario(aluno));
		return alunoRepository.save(aluno);
	}

	public void update(Long id, @Valid UpdateAlunoDTO dto) {
		userValidator.forEach(validator -> validator.validator(dto, id));
		Aluno alunoAtual = findById(id);
		BeanUtils.copyProperties(dto, alunoAtual, "id");
		alunoRepository.save(alunoAtual);
	}

	public void delete(Long id) {
		Aluno aluno = findById(id);
		aluno.setAtivo("N");
		alunoRepository.save(aluno);
	}

	public void reativarAluno(Long id) {
		Aluno aluno = findById(id);
		aluno.setAtivo("S");
		List<Mensalidade> mensalidadesAluno 
			= mensalidadeRepository.findByAluno(aluno);
		
		for(Mensalidade mensalidade : mensalidadesAluno) {
			if(mensalidade.getStatus() == StatusPagamento.VENCIDA.getCodigo()) {
				mensalidade.setStatus(StatusPagamento.ANULADA.getCodigo());
				mensalidadeRepository.save(mensalidade);
			}
		}
		alunoRepository.save(aluno);
	}

	
}
