package com.nexfi.ecommerce.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record ProdutoRequest(
		@NotBlank(message = "O nome do produto é obrigatorio!")
		String nome, 
		@PositiveOrZero(message = "O preço de venda do produto é obrigatorio e deve ser maior que 0!")
		BigDecimal precoVenda) {

}
