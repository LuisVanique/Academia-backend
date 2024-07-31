package com.br.luisvanique.academia.domain.aluno;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.br.luisvanique.academia.domain.Endereco;
import com.br.luisvanique.academia.domain.aluno.dto.CreateAlunoDTO;
import com.br.luisvanique.academia.domain.mensalidade.Mensalidade;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "TB_ALUNOS") // Definindo o nome da tabela
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
public class Aluno implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NOME")
	private String nome;
	@Embedded
	private Endereco endereco;

	@Column(name = "DATA_NASCIMENTO", unique = false)
	private LocalDate dataNascimento;
	
	@Column(name = "TELEFONE", unique = true)
	private String telefone;
	
	@Column(name = "DATA_CRIACAO")
	private LocalDate dataCriacao;
	
	@Column(name = "ATIVO")
	private String ativo;
	
	@OneToMany(mappedBy = "aluno")
	private List<Mensalidade> mensalidades;
	
	public Aluno(String nome, Endereco endereco ,String cpf, String telefone, LocalDate dataNascimento) {
		this.nome = nome;
		this.endereco = endereco;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.dataCriacao = LocalDate.now();
		this.ativo = "S";
	}
	
	public Aluno(CreateAlunoDTO dto) {
		this.nome = dto.nome();
		this.endereco = dto.endereco().toEndereco();
		this.dataNascimento = dto.dataNascimento();
		this.telefone = dto.telefone();
		this.dataCriacao = LocalDate.now();
		this.ativo = "S";
	}
}
