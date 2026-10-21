package com.nexfi.ecommerce.model;

public enum TipoMovimentacao {
	ENTRADA,
	SAIDA,
	AJUSTE_ENTRADA,
	AJUSTE_SAIDA;
	
	public boolean ehEntrada() {
		return this == ENTRADA || this == AJUSTE_ENTRADA;
	}
}
