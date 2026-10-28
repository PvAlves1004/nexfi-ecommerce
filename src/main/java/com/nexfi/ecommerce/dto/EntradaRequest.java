package com.nexfi.ecommerce.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record EntradaRequest(
		@Positive(message = "A quantidade de produtos deve ser maior que 0!")
		@NotNull(message = "É obrigatório digitar uma quantidade para entrada!")
		Integer quantidade,
		@PositiveOrZero(message = "O Preço de custo não pode ser negativo!")
		@NotNull(message = "É obrigatório informar um preço de custo!")
		BigDecimal custoUnitario) {

}
