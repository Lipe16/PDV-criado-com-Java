package com.ciadainformatica.vendas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class TipoDeVenda extends GenericDomain{
	
	@Column(nullable = false)
	private String tipo;//dinheiro, cartão de credito, cartão de debito, crediario
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Venda venda;//cupom da venda, valor, data

	
	
	
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	

}
