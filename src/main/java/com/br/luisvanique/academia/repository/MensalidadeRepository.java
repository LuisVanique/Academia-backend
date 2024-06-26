package com.br.luisvanique.academia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.luisvanique.academia.domain.Mensalidade;

public interface MensalidadeRepository 
	extends JpaRepository<Mensalidade, Long>{

}
