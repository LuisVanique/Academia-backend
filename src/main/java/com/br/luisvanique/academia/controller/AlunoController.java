package com.br.luisvanique.academia.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.luisvanique.academia.domain.aluno.Aluno;
import com.br.luisvanique.academia.domain.aluno.dto.AlunoDTO;
import com.br.luisvanique.academia.domain.aluno.dto.CreateAlunoDTO;
import com.br.luisvanique.academia.service.AlunoService;

import jakarta.validation.Valid;

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
	
	@PostMapping
	public ResponseEntity<AlunoDTO> create(@RequestBody @Valid CreateAlunoDTO dto){
		Aluno aluno = alunoService.create(dto);
		AlunoDTO dtoAluno = new AlunoDTO(aluno);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(dtoAluno.id()).toUri();
		return ResponseEntity.created(uri).body(dtoAluno);
	}
	
	//TODO: END-POINT UPDATE
	//TODO: END-POINT DELETE
	//TODO: END-POINT FINDBYID
	
	
}
