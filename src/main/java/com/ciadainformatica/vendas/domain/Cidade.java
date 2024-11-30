package com.ciadainformatica.vendas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")// ignora warnings deste tipo
@Entity //diz: é uma entidade do hibernate e gerará uma tabela
public class Cidade extends GenericDomain{
	
	//diz que nome tem tamanho 50 e não pode ser nulo
	@Column(length = 50, nullable = false, unique=true)
	private String nome;
	
	
	@ManyToOne // diz que varias cidades podem ter um único estado, Estado é a chave estrangeira
	@JoinColumn(nullable = false)// serve para personalizar colunas que tem chave estrangeira, está impedindo que a chave estrangeira seja nula, ou seja, está impedindo de a cidade ficar sem chave estrangeira 
	private Estado estado;
	

	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	
	
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
}
