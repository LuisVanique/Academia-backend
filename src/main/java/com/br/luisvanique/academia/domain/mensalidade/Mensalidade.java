package com.br.luisvanique.academia.domain.mensalidade;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.br.luisvanique.academia.domain.aluno.Aluno;
import com.br.luisvanique.academia.domain.enums.StatusPagamento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "mensalidades")
public class Mensalidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "aluno_id")
    private Aluno aluno;
	
	
	@Column(name = "DATA_VENCIMENTO")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataVencimento;
	
	@Column(name= "VALOR")
	private Double valor;
	
	@Column(name = "STATUS")
	private Integer status;

}
