package com.ciadainformatica.vendas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")//ignora este tipo de warning
@Entity // serve para dizer que o estado é uma entidade do hibernate
public class Estado extends GenericDomain{
	
	@Column(length = 50, nullable = false, unique=true) // diz que o nome deve ter tamanho de 2 caracteres e não poderá ser nulo
	private String nome;
	
	@Column(length = 3, nullable = false, unique=true) // diz que a sigla deve ter tamanho de 50 caracteres e não poderá ser nulo
	private String sigla;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	
	
}
