package com.ciadainformatica.vendas.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.ciadainformatica.vendas.domain.ItemVenda;
import com.ciadainformatica.vendas.domain.Venda;
import com.ciadainformatica.vendas.util.HibernateUtil;

public class VendaDAO extends GenericDAO<Venda>{
	
	
	Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
	
	
	//Operação de listar para as entidades
	@SuppressWarnings("unchecked")
	public List listarPorData(Date dataInicio, Date dataFim){

	
		
		try{
			//captura os critérios de consulta
			Criteria consulta = sessao.createCriteria(Venda.class);
			

			consulta.add(Restrictions.ge("horario",dataInicio));// coloca restrições de busca
		     
		    consulta.add( Restrictions.le("horario",dataFim));
			
			// joga os resultados numa lista
			List resultado = consulta.list();
			// retorna o resultado
			return resultado;	
		}catch(RuntimeException erro){
			throw erro;//display error message
		}finally {
			sessao.close();//libera recursos 
		}	
	}

}
