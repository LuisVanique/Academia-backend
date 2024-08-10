package com.br.luisvanique.academia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.br.luisvanique.academia.domain.instrutor.Instrutor;

@Repository
public interface InstrutorRepository 
	extends JpaRepository<Instrutor, Long>{

	boolean existsByEmail(String email);

	Instrutor findByEmail(String email);

}
