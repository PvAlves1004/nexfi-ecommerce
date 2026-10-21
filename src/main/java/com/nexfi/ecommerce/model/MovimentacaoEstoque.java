package com.nexfi.ecommerce.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.nexfi.ecommerce.exception.EstoqueInsuficienteException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mov_estoque")
public class MovimentacaoEstoque {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private OffsetDateTime data;

	private String motivo;

	private Integer quantidade;

	private Integer quantidadeRestante;

	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipo;

	@Column(precision = 10, scale = 2)
	private BigDecimal custoUnitario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "produto_id", nullable = false)
	private Produto produto;

	private MovimentacaoEstoque(Produto produto, TipoMovimentacao tipo, Integer quantidade, BigDecimal custoUnitario,
			Integer quantidadeRestante, String motivo) {

		this.produto = produto;
		this.tipo = tipo;
		this.quantidade = quantidade;
		this.custoUnitario = custoUnitario;
		this.quantidadeRestante = quantidadeRestante;
		this.motivo = motivo;

	}

	protected MovimentacaoEstoque() {

	}

	public String getMotivo() {
		return motivo;
	}

	public Long getId() {
		return id;
	}

	public OffsetDateTime getData() {
		return data;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Integer getQuantidadeRestante() {
		return quantidadeRestante;
	}

	public TipoMovimentacao getTipo() {
		return tipo;
	}

	public BigDecimal getCustoUnitario() {
		return custoUnitario;
	}

	public Produto getProduto() {
		return produto;
	}

	public void consumir(Integer quantidadeConsumida) {
		if (!tipo.ehEntrada()) {
			throw new IllegalStateException("Tipo de objeto incorreto");
		}
		if (quantidadeConsumida <= 0) {
			throw new IllegalArgumentException("Quantidade invalida!");
		}
		if (this.quantidadeRestante - quantidadeConsumida < 0) {
			throw new EstoqueInsuficienteException("Estoque insuficiente!");
		}
		this.quantidadeRestante = this.quantidadeRestante - quantidadeConsumida;
	}

	public void devolver(Integer quantidadeDevolvida) {
		if (!tipo.ehEntrada()) {
			throw new IllegalStateException("Tipo de objeto incorreto");
		}
		if (quantidadeDevolvida <= 0) {
			throw new IllegalArgumentException("Quantidade invalida!");
		}
		if (this.quantidadeRestante + quantidadeDevolvida > this.quantidade) {
			throw new IllegalStateException("Quantidade incompativel com o lote");
		}

		this.quantidadeRestante = this.quantidadeRestante + quantidadeDevolvida;
	}

	public static MovimentacaoEstoque saida(Produto produto, Integer quantidade) {
		return new MovimentacaoEstoque(produto, TipoMovimentacao.SAIDA, quantidade, null, null, null);
	}

	public static MovimentacaoEstoque entrada(Produto produto, Integer quantidade, BigDecimal custoUnitario) {
		return new MovimentacaoEstoque(produto, TipoMovimentacao.ENTRADA, quantidade, custoUnitario, quantidade, null);
	}

	public static MovimentacaoEstoque ajusteSaida(Produto produto, Integer quantidade, String motivo) {
		return new MovimentacaoEstoque(produto, TipoMovimentacao.AJUSTE_SAIDA, quantidade, null, null, motivo);
	}

	public static MovimentacaoEstoque ajusteEntrada(Produto produto, Integer quantidade, BigDecimal custoUnitario,
			String motivo) {
		return new MovimentacaoEstoque(produto, TipoMovimentacao.AJUSTE_ENTRADA, quantidade, custoUnitario, quantidade,
				motivo);
	}

}
