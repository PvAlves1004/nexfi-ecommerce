package com.nexfi.ecommerce.service;

import org.springframework.stereotype.Service;

import com.nexfi.ecommerce.dto.EntradaRequest;
import com.nexfi.ecommerce.dto.MovimentacaoResponse;
import com.nexfi.ecommerce.exception.RecursoNaoEncontradoException;
import com.nexfi.ecommerce.model.MovimentacaoEstoque;
import com.nexfi.ecommerce.model.Produto;
import com.nexfi.ecommerce.repository.MovimentacaoEstoqueRepository;
import com.nexfi.ecommerce.repository.ProdutoRepository;

@Service
public class EstoqueService {
	
	private final ProdutoRepository produtoRepository;
	private final MovimentacaoEstoqueRepository movEstoqueRepository;

	public EstoqueService(ProdutoRepository produtoRepository, MovimentacaoEstoqueRepository movEstoqueRepository) {
		this.produtoRepository = produtoRepository;
		this.movEstoqueRepository = movEstoqueRepository;
	}
	
	public MovimentacaoResponse registrarEntrada(Long lojaId, Long produtoId, EntradaRequest entradaRequest) {
		Produto produto = produtoRepository.findByLojaIdAndId(lojaId, produtoId)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado!"));
		MovimentacaoEstoque movEntrada = MovimentacaoEstoque.entrada(produto, entradaRequest.quantidade(), entradaRequest.custoUnitario());
		MovimentacaoEstoque entradaRegistrada = movEstoqueRepository.save(movEntrada);
		return MovimentacaoResponse.de(entradaRegistrada);
	}	
}
