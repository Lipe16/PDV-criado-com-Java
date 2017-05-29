package com.ciadainformatica.vendas.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")// ignora warning deste tipo
@Entity // serve para dizer que o Fabricante é uma entidade do hibernate, atraves dele será gerada uma tabela
public class Funcionario extends GenericDomain{
	@Column(nullable = false)// não poderá ter campo nulo na table
	private String carteiraTrabalho;
	
	@Column(nullable = false)// não poderá ter campo nulo na table
	@Temporal(TemporalType.DATE)//campo tipo data na tabela
	private Date dataAdmissao;
	
	@JoinColumn(nullable = false, unique = true)// não poderá ter campo nulo na table
	@OneToOne //relação entre tabelas: um usuario podera ser apenas uma pessoa e vice versa
	private Pessoa pessoa;

	
	public String getCarteiraTrabalho() {
		return carteiraTrabalho;
	}
	public void setCarteiraTrabalho(String carteiraTrabalho) {
		this.carteiraTrabalho = carteiraTrabalho;
	}

	
	public Date getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
}
