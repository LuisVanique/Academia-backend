package com.br.luisvanique.academia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.luisvanique.academia.domain.aluno.Aluno;

import jakarta.validation.constraints.NotBlank;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{

	boolean existsByTelefone(@NotBlank String telefone);
	
	Optional<Aluno> findByTelefone(@NotBlank String telefone);
	
}
