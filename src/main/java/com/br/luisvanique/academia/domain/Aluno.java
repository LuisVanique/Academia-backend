package com.br.luisvanique.academia.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
public class Aluno implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NOME")
	private String nome;
	@Embedded
	private Endereco endereco;
	@Column(name = "CPF")
	private String cpf;
	
	@Column(name = "TELEFONE")
	private String telefone;
	
	@Column(name = "DATA_CRIACAO")
	private LocalDate dataCriacao;
	
	@OneToMany(mappedBy = "aluno")
	private List<Mensalidade> mensalidades;
	
	public Aluno(String nome, Endereco endereco ,String cpf, String telefone) {
		this.nome = nome;
		this.endereco = endereco;
		this.cpf = cpf;
		this.telefone = telefone;
		this.dataCriacao = LocalDate.now();
	}
}
