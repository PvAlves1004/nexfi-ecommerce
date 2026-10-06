package com.nexfi.ecommerce.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loja_id", nullable = false)
	private Loja loja;

	@Column(nullable = false)
	private String nome;

	@Column(precision = 10, scale = 2)
	private BigDecimal precoCusto;

	@Column(precision = 10, scale = 2, nullable = false)
	private BigDecimal precoVenda;

	protected Produto() {
	}

	public Produto(Loja loja, String nome, BigDecimal precoVenda) {
		this.loja = loja;
		this.nome = nome;
		this.precoVenda = precoVenda;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Loja getLoja() {
		return loja;
	}

	public BigDecimal getPrecoCusto() {
		return precoCusto;
	}
}
