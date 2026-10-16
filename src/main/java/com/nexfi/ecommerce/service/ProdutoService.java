package com.nexfi.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nexfi.ecommerce.dto.ProdutoRequest;
import com.nexfi.ecommerce.dto.ProdutoResponse;
import com.nexfi.ecommerce.exception.ConflitoException;
import com.nexfi.ecommerce.exception.RecursoNaoEncontradoException;
import com.nexfi.ecommerce.model.Loja;
import com.nexfi.ecommerce.model.Produto;
import com.nexfi.ecommerce.repository.LojaRepository;
import com.nexfi.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {

	private final ProdutoRepository produtoRepository;
	private final LojaRepository lojaRepository;

	public ProdutoService(ProdutoRepository produtoRepository, LojaRepository lojaRepository) {
		this.produtoRepository = produtoRepository;
		this.lojaRepository = lojaRepository;
	}

	public ProdutoResponse cadastrar(ProdutoRequest produtoRequest, Long lojaId) {
		
		Loja loja = lojaRepository.findById(lojaId)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Loja não encontrada"));
		if(produtoRepository.existsByLojaIdAndNome(lojaId, produtoRequest.nome())) {
			throw new ConflitoException("Já existe um produto com esse nome nesta loja");
		}
		
		Produto produto = new Produto(loja, produtoRequest.nome(), produtoRequest.precoVenda());
		
		Produto criado = produtoRepository.save(produto);
		return ProdutoResponse.de(criado);
	}
	
	public List<ProdutoResponse> listar(Long lojaId){
		
		if(!lojaRepository.existsById(lojaId)) {
			throw new RecursoNaoEncontradoException("Loja não existente");
		}
		return produtoRepository.findByLojaId(lojaId)
				.stream()
				.map(ProdutoResponse::de)
				.toList();
	}
	
	public ProdutoResponse buscarPorId(Long lojaId, Long id) {
		
		return produtoRepository.findByLojaIdAndId(lojaId, id)
				.map(ProdutoResponse::de)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado"));
		
	}

}