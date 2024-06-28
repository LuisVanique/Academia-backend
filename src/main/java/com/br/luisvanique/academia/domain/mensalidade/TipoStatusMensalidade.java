package com.br.luisvanique.academia.domain.mensalidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "TIPO_STATUS_MENSALIDADE")
public class TipoStatusMensalidade {
	
	@Id
	private Integer id;
	@Column(name = "Descricao")
	private String descricao;
	
	public TipoStatusMensalidade(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
}
