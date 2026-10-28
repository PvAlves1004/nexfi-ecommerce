package com.nexfi.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexfi.ecommerce.model.MovimentacaoEstoque;

public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, Long> {
	
	List<MovimentacaoEstoque> findByProdutoIdAndQuantidadeRestanteGreaterThanOrderByDataAscIdAsc(Long produtoId, Integer saldoMin);

}
