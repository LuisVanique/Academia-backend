package com.br.luisvanique.academia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.luisvanique.academia.domain.aluno.Aluno;
import com.br.luisvanique.academia.domain.mensalidade.Mensalidade;

@Repository
public interface MensalidadeRepository 
	extends JpaRepository<Mensalidade, Long>{

	@Query("select u from Mensalidade u where :status = u.status")
	List<Mensalidade> findByFiltrosStatus(@Param("status") Integer status);
	
	List<Mensalidade> findByAluno(Aluno aluno);
}
