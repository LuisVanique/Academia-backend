package com.br.luisvanique.academia.controller.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.br.luisvanique.academia.controller.dto.ErrorDTO;
import com.br.luisvanique.academia.controller.dto.MessageErrorDTO;
import com.br.luisvanique.academia.domain.aluno.exception.CpfJaRegistradoException;
import com.br.luisvanique.academia.domain.aluno.exception.TelefoneJaRegistradoException;

@RestControllerAdvice
public class ControllerAdviceHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ErrorDTO>> handleCamposNaoPreenchidos(MethodArgumentNotValidException ex){
		List<ErrorDTO> errors = new ArrayList<>();
		for(FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(new ErrorDTO(error.getField(), error.getDefaultMessage()));
		}
		return new ResponseEntity<List<ErrorDTO>>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CpfJaRegistradoException.class)
	public ResponseEntity<MessageErrorDTO> handleCpfCadastrado(CpfJaRegistradoException ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.contentType(MediaType.APPLICATION_JSON).
				body(new MessageErrorDTO(ex.getMessage()));
	}
	
	@ExceptionHandler(TelefoneJaRegistradoException.class)
	public ResponseEntity<MessageErrorDTO> handleTelefoneCadastrado(TelefoneJaRegistradoException ex){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
				.body(new MessageErrorDTO(ex.getMessage()));
	}
	
}
