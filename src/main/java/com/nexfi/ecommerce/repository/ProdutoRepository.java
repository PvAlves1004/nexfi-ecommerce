package com.nexfi.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexfi.ecommerce.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	public boolean existsByLojaIdAndNome(Long id, String nome);
	public List<Produto> findByLojaId(Long id);
	public Optional<Produto> findByLojaIdAndId(Long lojaId, Long id);
	
}
