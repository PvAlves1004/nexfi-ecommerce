package com.nexfi.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LojaRequest(
		
		@Size(max = 255, message = "O nome deve ter no máximo 255 caracteres") 
		@NotBlank(message = "O nome é obrigatorio")
		String nome, 
		@Pattern(regexp = "\\d{14}", message = "O CNPJ deve conter 14 digitos sem pontos ou traços") 
		@NotBlank(message = "O CNPJ é obrigatorio") 
		String cnpj) {}


