package com.ciadainformatica.vendas.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@SuppressWarnings("serial")//impede warnings do tipo serial neste metodo
@MappedSuperclass // a anotação diz que esta classe não corresponde a uma tabela, mas ela sera utilizada pro outros para gerar tabelas
public class GenericDomain implements Serializable{
	
	@Id // serve para dizer pro código que ele é uma chave primária
	@GeneratedValue(strategy = GenerationType.AUTO) // diz que Id será gerado automaticamente pelo banco
	private Long codigo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	//para o primeFaces
	@Override
	public String toString() {
		return String.format("%s[codigo=%d]", getClass().getSimpleName(), getCodigo());
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericDomain other = (GenericDomain) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
	 
}
