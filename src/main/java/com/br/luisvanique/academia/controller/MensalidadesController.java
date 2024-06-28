package com.br.luisvanique.academia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.luisvanique.academia.domain.mensalidade.Mensalidade;
import com.br.luisvanique.academia.domain.mensalidade.dto.AprovarPagamentoDTO;
import com.br.luisvanique.academia.domain.mensalidade.dto.MensalidadeDTO;
import com.br.luisvanique.academia.service.MensalidadeService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/mensalidade")
public class MensalidadesController {
	
	@Autowired
	private MensalidadeService mensalidadeService;
	
	
	@GetMapping
	public ResponseEntity<List<MensalidadeDTO>> findByFiltroStatus(@RequestParam(required = false) Integer status){
		List<Mensalidade> mensalidades = mensalidadeService.findByFiltroStatus(status);
		List<MensalidadeDTO> mensalidadesDTO = 
				mensalidades.stream().map(x -> new MensalidadeDTO(x)).toList();
		return ResponseEntity.ok().body(mensalidadesDTO);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<MensalidadeDTO> aprovarPagamento(@PathVariable Long id, 
			@RequestBody @Valid AprovarPagamentoDTO status){
		mensalidadeService.aprovarPagamento(id, status);
		return ResponseEntity.ok().build();
	}
	
}
