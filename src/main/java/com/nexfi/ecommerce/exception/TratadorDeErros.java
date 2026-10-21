package com.nexfi.ecommerce.exception;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {
	
	@ExceptionHandler(ConflitoException.class)
	public ProblemDetail tratarConflito(ConflitoException e) {
		return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
	}
	
	@ExceptionHandler(RecursoNaoEncontradoException.class)
	public ProblemDetail tratarRecursoNaoEncontrado(RecursoNaoEncontradoException e) {
		return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ProblemDetail tratarDadosInvalidos(MethodArgumentNotValidException e) {
		
		ProblemDetail problema = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,"Dados Inválidos");
		
		Map<String, List<String>> campos = e.getBindingResult().getFieldErrors()
			.stream()
			.collect(Collectors.groupingBy(FieldError::getField,
					Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())));
		problema.setProperty("campos", campos);
		return problema;
	}
	
	@ExceptionHandler(EstoqueInsuficienteException.class)
	public ProblemDetail tratarEstoqueInsuficiente(EstoqueInsuficienteException e) {
		return ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_CONTENT, e.getMessage());
	}
	

}
