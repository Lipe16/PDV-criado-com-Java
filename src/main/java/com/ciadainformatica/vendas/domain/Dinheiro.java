package com.ciadainformatica.vendas.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Dinheiro extends GenericDomain{
	
	@Column(nullable = false)
	private BigDecimal dinheiro;

	
	public BigDecimal getDinheiro() {
		return dinheiro;
	}

	public void setDinheiro(BigDecimal dinheiro) {
		this.dinheiro = dinheiro;
	}
	
	

}
