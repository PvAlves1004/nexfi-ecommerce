package com.nexfi.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexfi.ecommerce.model.Loja;

public interface LojaRepository extends JpaRepository<Loja, Long> {
	
	public boolean existsByCnpj(String cnpj);

}
