package com.ciadainformatica.vendas.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")// ignora warnings deste tipo
@Entity //diz: é uma entidade do hibernate, é uma tabela
public class Cliente  extends GenericDomain{
	
	@Column(nullable = false)//impede que o campo seja nulo
	@Temporal(TemporalType.DATE)// especifica tipo data para  campo na tabela
	private Date dataCadastro;
	
	@Column(nullable = false)//impede que o campo seja nulo
	private Boolean liberado;
	
	@JoinColumn(nullable = false, unique = true)//impede que a chave estrangeira seja nula
	@OneToOne // diz: um cliente só poderá ter uma pessoa, assim como uma pessoa só poderá ser um cliente
	private Pessoa pessoa;

	
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	
	public Boolean getLiberado() {
		return liberado;
	}
	public void setLiberado(Boolean liberado) {
		this.liberado = liberado;
	}

	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	
}
