package com.nexfi.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AjusteSaidaRequest(
		@NotBlank(message = "O motivo para este ajuste é obrigatório") 
		String motivo,
		@Positive(message = "A quantidade deste ajuste deve ser maior que 0")
		@NotNull(message = "é obrigatório digitar uma quantidade para ajuste")
		Integer quantidade) {
}
