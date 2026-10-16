package com.nexfi.ecommerce.dto;

import java.math.BigDecimal;

import com.nexfi.ecommerce.model.Produto;

public record ProdutoResponse(
		Long id,
		String nome,
		BigDecimal precoCusto,
		BigDecimal precoVenda, 
		Long lojaId) {
	
		
		public static ProdutoResponse de(Produto produto) {
			return new ProdutoResponse(
					produto.getId(),
					produto.getNome(),
					produto.getPrecoCusto(),
					produto.getPrecoVenda(),
					produto.getLoja().getId()
					);
		
		}
	
}

