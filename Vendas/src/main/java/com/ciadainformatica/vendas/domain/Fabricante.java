package com.ciadainformatica.vendas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")// ignora warning deste tipo
@Entity // serve para dizer que o Fabricante é uma entidade do hibernate, atraves dele será gerada uma tabela
public class Fabricante extends GenericDomain{
	@Column(length = 50, nullable = false, unique=true) // diz: descricao tera tamanho 50 e nao podera ser nula
	private String descricao;

	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
