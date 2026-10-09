package com.nexfi.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexfi.ecommerce.dto.LojaRequest;
import com.nexfi.ecommerce.dto.LojaResponse;
import com.nexfi.ecommerce.service.LojaService;

@RestController
@RequestMapping("/lojas")
public class LojaController {
	
	private final LojaService lojaService;
	
	public LojaController(LojaService lojaService) {
		this.lojaService = lojaService;
	}
	
	@PostMapping
	public ResponseEntity<LojaResponse> criar(@RequestBody @Validated LojaRequest lojaRequest){
		return ResponseEntity.status(HttpStatus.CREATED).body(lojaService.criar(lojaRequest));
	}
	
	@GetMapping
	public List<LojaResponse> listar(){
		return lojaService.listar();
	}
	
	@GetMapping("/{id}")
	public LojaResponse buscarPorId(@PathVariable Long id) {
		return lojaService.buscarPorId(id);
	}
	
	
	
	

}
