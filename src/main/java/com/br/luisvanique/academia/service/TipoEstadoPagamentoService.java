package com.br.luisvanique.academia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.luisvanique.academia.domain.mensalidade.TipoStatusMensalidade;
import com.br.luisvanique.academia.repository.TipoStatusPagamentoRepository;

@Service
public class TipoEstadoPagamentoService {
	
	@Autowired
	private TipoStatusPagamentoRepository tipoStatusPagamentoRepository;
	
	public List<TipoStatusMensalidade> findAllStatusMensalidade(){
		return tipoStatusPagamentoRepository.findAll();
	}
}
