package com.br.luisvanique.academia.domain.instrutor;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.br.luisvanique.academia.controller.dto.CreateInstrutorDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TB_INSTRUTORES")
public class Instrutor implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "SENHA")
	private String senha;
	
	private String nome;

	@Column(name = "PERFIL")
	private String perfil = "INSTRUTOR";
	
	public Instrutor(){
		
	}
	
	public Instrutor(CreateInstrutorDTO dto) {
		this.nome = dto.nome();
		this.email = dto.email();
		this.senha = dto.senha();
		this.nome = dto.nome();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(this.perfil.toString()));
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}
}
