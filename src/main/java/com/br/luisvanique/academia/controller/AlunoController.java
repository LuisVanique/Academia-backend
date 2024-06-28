package com.br.luisvanique.academia.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.luisvanique.academia.domain.aluno.Aluno;
import com.br.luisvanique.academia.domain.aluno.dto.AlunoDTO;
import com.br.luisvanique.academia.domain.aluno.dto.CreateAlunoDTO;
import com.br.luisvanique.academia.domain.aluno.dto.UpdateAlunoDTO;
import com.br.luisvanique.academia.service.AlunoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;

	@GetMapping
	public ResponseEntity<Page<AlunoDTO>> findAll(@PageableDefault Pageable pageable){
		Page<Aluno> alunos = alunoService.findAll(pageable);
		Page<AlunoDTO> alunosDTO = alunos.map(AlunoDTO::new);
		return ResponseEntity.ok().body(alunosDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AlunoDTO> findById(@PathVariable Long id){
		Aluno aluno = alunoService.findById(id);
		AlunoDTO dto = new AlunoDTO(aluno);
		return ResponseEntity.ok().body(dto);
		
	}
	
	@PostMapping
	public ResponseEntity<AlunoDTO> create(@RequestBody @Valid CreateAlunoDTO dto){
		Aluno aluno = alunoService.create(dto);
		AlunoDTO dtoAluno = new AlunoDTO(aluno);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(dtoAluno.id()).toUri();
		return ResponseEntity.created(uri).body(dtoAluno);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AlunoDTO> update(@PathVariable Long id, @RequestBody @Valid UpdateAlunoDTO dto){
		alunoService.update(id, dto);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<AlunoDTO> delete(@PathVariable Long id){
		alunoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/reativar/{id}")
	public ResponseEntity<AlunoDTO> reativarAluno(@PathVariable Long id){
		alunoService.reativarAluno(id);
		return ResponseEntity.noContent().build();
		
	}
	
	
	
}
