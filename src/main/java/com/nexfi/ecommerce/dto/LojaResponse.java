package com.nexfi.ecommerce.dto;

import com.nexfi.ecommerce.model.Loja;

public record LojaResponse(Long id, String nome, String cnpj) {
	

	public static LojaResponse de(Loja loja) {
		return new LojaResponse(
				loja.getId(),
				loja.getNome(),
				loja.getCnpj()
				);
				
				
				
	}

}
