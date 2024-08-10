package com.br.luisvanique.academia.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.luisvanique.academia.controller.dto.CreateInstrutorDTO;
import com.br.luisvanique.academia.domain.instrutor.Instrutor;
import com.br.luisvanique.academia.domain.instrutor.InstrutorDTO;
import com.br.luisvanique.academia.service.InstrutorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/instrutor")
public class InstrutorController {
	@Autowired
	public InstrutorService instrutorService;

	@PostMapping
	public ResponseEntity<InstrutorDTO> create(@RequestBody @Valid CreateInstrutorDTO dto){
		Instrutor instrutor = instrutorService.create(dto);
		InstrutorDTO instrutorDTO = new InstrutorDTO(instrutor);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(instrutorDTO.id()).toUri();
		return ResponseEntity.created(uri).body(instrutorDTO);
	}
}
