package com.nexfi.ecommerce.service;



import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nexfi.ecommerce.dto.LojaRequest;
import com.nexfi.ecommerce.dto.LojaResponse;
import com.nexfi.ecommerce.exception.ConflitoException;
import com.nexfi.ecommerce.exception.RecursoNaoEncontradoException;
import com.nexfi.ecommerce.model.Loja;
import com.nexfi.ecommerce.repository.LojaRepository;

@Service
public class LojaService {

	private final LojaRepository lojaRepository;

	public LojaService(LojaRepository lojaRepository) {
		this.lojaRepository = lojaRepository;
	}

	public LojaResponse criar(LojaRequest lojaRequest) {

		if (lojaRepository.existsByCnpj(lojaRequest.cnpj())) {
			throw new ConflitoException("CNPJ ja cadastrado!");
		}
		Loja loja = new Loja(lojaRequest.cnpj(), lojaRequest.nome());
		Loja lojaSalva = lojaRepository.save(loja);
		return LojaResponse.de(lojaSalva);
	}
	
	public List<LojaResponse> listar() {
		return lojaRepository.findAll()
				.stream()
				.map(LojaResponse::de)
				.toList();		
	}
	
	public LojaResponse buscarPorId(Long id){
				
		return lojaRepository.findById(id)
				.map(LojaResponse::de)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Loja não encontrada"));
				
	}

}
