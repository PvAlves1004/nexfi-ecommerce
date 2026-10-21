package com.nexfi.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "controle_lotes")
public class ControleLote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "saida_id")
	private MovimentacaoEstoque saida;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lote_id")
	private MovimentacaoEstoque lote;

	private Integer quantidade;
	
	protected ControleLote() {

	}

	public ControleLote(MovimentacaoEstoque saida, MovimentacaoEstoque lote, Integer quantidade) {
		this.saida = saida;
		this.lote = lote;
		this.quantidade = quantidade;
	}


	public Long getId() {
		return id;
	}

	public MovimentacaoEstoque getSaida() {
		return saida;
	}

	public MovimentacaoEstoque getLote() {
		return lote;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

}
