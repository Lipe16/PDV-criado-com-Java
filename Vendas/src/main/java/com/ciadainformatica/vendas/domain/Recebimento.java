package com.ciadainformatica.vendas.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")// ignora warnings deste tipo
@Entity
public class Recebimento extends GenericDomain{
	
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Venda venda;
	
	@Column(nullable = false)
	private String parcela;
	
	@Column(nullable = false)
	private BigDecimal valor;
	
	@Column(nullable = false)
	private Date dataDoRecebimento;

	@Column(nullable = false)
	private String formaDeRecebimento;
	
	
	
	
	public String getFormaDeRecebimento() {
		return formaDeRecebimento;
	}

	public void setFormaDeRecebimento(String formaDeRecebimento) {
		this.formaDeRecebimento = formaDeRecebimento;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public String getParcela() {
		return parcela;
	}

	public void setParcela(String parcela) {
		this.parcela = parcela;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getDataDoRecebimento() {
		return dataDoRecebimento;
	}

	public void setDataDoRecebimento(Date dataDoRecebimento) {
		this.dataDoRecebimento = dataDoRecebimento;
	}
	
	
}
