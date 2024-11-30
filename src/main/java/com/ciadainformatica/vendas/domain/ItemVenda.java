package com.ciadainformatica.vendas.domain;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")// ignora warning deste tipo
@Entity // serve para dizer que o ItemVenda é uma entidade do hibernate, atraves dele será gerada uma tabela
public class ItemVenda extends GenericDomain{
	
	@Column(nullable = false)
	private Short quantidade;
	
	@Column(nullable = false)
	private BigDecimal valorParcial;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Produto produto;
	

	@ManyToOne
	private Funcionario funcionario;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Venda venda;

	
	
	public Short getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Short quantidade) {
		this.quantidade = quantidade;
	}

	
	
	public BigDecimal getValorParcial() {
		return valorParcial;
	}
	public void setValorParcial(BigDecimal valorParcial) {
		this.valorParcial = valorParcial;
	}

	
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	
}
