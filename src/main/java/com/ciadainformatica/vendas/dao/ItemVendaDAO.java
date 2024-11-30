package com.ciadainformatica.vendas.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.ciadainformatica.vendas.domain.ItemVenda;
import com.ciadainformatica.vendas.util.HibernateUtil;


public class ItemVendaDAO extends GenericDAO<ItemVenda>{
	
	Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

	//Operação de listar para as entidades
	@SuppressWarnings("unchecked")
	public List<ItemVenda> listarPorData(Date dataInicio, Date dataFim){

		
		try{
			//captura os critérios de consulta
			Criteria consulta = sessao.createCriteria(ItemVenda.class);
			
			consulta.createAlias("venda", "v");

			consulta.add(Restrictions.ge("v.horario",dataInicio));// coloca restrições de busca
		     
		    consulta.add( Restrictions.le("v.horario",dataFim));
			
			// joga os resultados numa lista
			List<ItemVenda> resultado = consulta.list();
			// retorna o resultado
			return resultado;	
		}catch(RuntimeException erro){
			throw erro;//display error message
		}finally {
			sessao.close();//libera recursos 
		}	
	}
	
	
	
	public List<ItemVenda> buscarPorCodigoDeVenda(long codigo){
		
		try{
			//captura os critérios de consulta
			Criteria consulta = sessao.createCriteria(ItemVenda.class);
			
			consulta.createAlias("venda", "v");

			consulta.add(Restrictions.like("v.codigo",codigo));// coloca restrições de busca
		     

			// joga os resultados numa lista
			List<ItemVenda> resultado = consulta.list();
			return resultado;
		}catch(RuntimeException erro){
			throw erro;//display error message
		}finally {
			sessao.close();//libera recursos 
		}	
	}
		
	
}
