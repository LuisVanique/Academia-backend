package com.br.luisvanique.academia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.luisvanique.academia.domain.instrutor.Instrutor;

@Repository
public interface InstrutorRepository 
	extends JpaRepository<Instrutor, Long>{

}
