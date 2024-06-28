package com.br.luisvanique.academia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.luisvanique.academia.domain.mensalidade.TipoStatusMensalidade;

@Repository
public interface TipoStatusPagamentoRepository extends JpaRepository<TipoStatusMensalidade, Integer>{

}
