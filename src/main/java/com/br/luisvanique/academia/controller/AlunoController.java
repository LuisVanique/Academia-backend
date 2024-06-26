package com.br.luisvanique.academia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.luisvanique.academia.domain.Aluno;
import com.br.luisvanique.academia.domain.dto.AlunoDTO;
import com.br.luisvanique.academia.service.AlunoService;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;

	@GetMapping
	public ResponseEntity<List<AlunoDTO>> findAll(){
		List<Aluno> alunos = alunoService.findAll();
		List<AlunoDTO> alunosDTO = alunos.stream().map(x -> new AlunoDTO(x)).toList();
		return ResponseEntity.ok().body(alunosDTO);
	}
	
	
}
