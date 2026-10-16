package com.nexfi.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexfi.ecommerce.dto.ProdutoRequest;
import com.nexfi.ecommerce.dto.ProdutoResponse;
import com.nexfi.ecommerce.service.ProdutoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/lojas/{lojaId}/produtos")
public class ProdutoController {
	
	private final ProdutoService produtoService;
	
	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}
	
	@PostMapping
	public ResponseEntity<ProdutoResponse> cadastrar(@RequestBody @Valid ProdutoRequest produtoRequest, @PathVariable Long lojaId){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(produtoService.cadastrar(produtoRequest, lojaId ));
	}
	
	@GetMapping
	public List<ProdutoResponse> listar(@PathVariable Long lojaId){
		return produtoService.listar(lojaId);
	}
	
	@GetMapping("/{id}")
	public ProdutoResponse buscarPorId(@PathVariable Long lojaId, @PathVariable Long id) {
		return produtoService.buscarPorId(lojaId, id);
		
	}
		
		
}
