package com.ciadainformatica.vendas.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.ciadainformatica.vendas.domain.Recebimento;
import com.ciadainformatica.vendas.domain.TipoDeVenda;
import com.ciadainformatica.vendas.util.HibernateUtil;

public class RecebimentoDAO  extends GenericDAO<Recebimento>{
	
	Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

	//Operação de listar para as entidades
	@SuppressWarnings("unchecked")
	public List listarPorData(Date dataInicio, Date dataFim){


				
				try{
					
					//captura os critérios de consulta
					Criteria consulta = sessao.createCriteria(Recebimento.class);
					
					
					//consulta.add(Restrictions.eq("tipo", tipo));// tipo de venda
					
					consulta.add(Restrictions.ge("dataDoRecebimento",dataInicio));// coloca restrições de busca
				     
				    consulta.add(Restrictions.le("dataDoRecebimento",dataFim));
					
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
