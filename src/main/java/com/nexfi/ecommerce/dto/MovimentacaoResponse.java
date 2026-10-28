package com.nexfi.ecommerce.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.nexfi.ecommerce.model.MovimentacaoEstoque;
import com.nexfi.ecommerce.model.TipoMovimentacao;

public record MovimentacaoResponse(
		Long id,
		OffsetDateTime data,
		String motivo, 
		Integer quantidade, 
		Integer quantidadeRestante,
		TipoMovimentacao tipo,
		BigDecimal custoUnitario, 
		Long produtoId
		) {

	public static MovimentacaoResponse de(MovimentacaoEstoque movimentacao) {
		return new MovimentacaoResponse(
				movimentacao.getId(),
				movimentacao.getData(),  
				movimentacao.getMotivo(), 
				movimentacao.getQuantidade(),
				movimentacao.getQuantidadeRestante(),
				movimentacao.getTipo(),
				movimentacao.getCustoUnitario(),
				movimentacao.getProduto().getId()
				);
	}

}
