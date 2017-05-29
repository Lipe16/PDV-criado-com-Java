package com.ciadainformatica.vendas.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.ciadainformatica.vendas.domain.TipoDeVenda;
import com.ciadainformatica.vendas.domain.Venda;
import com.ciadainformatica.vendas.util.HibernateUtil;

public class TipoDeVendaDAO extends GenericDAO<TipoDeVenda>{
	
	Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
	
	
	
	//Operação de listar para as entidades
	@SuppressWarnings("unchecked")
	public TipoDeVenda buscarPorVenda(Venda venda){
		TipoDeVenda tipoDeVenda = new TipoDeVenda();
		
				
				try{
					
					//captura os critérios de consulta
					Criteria consulta = sessao.createCriteria(TipoDeVenda.class);
					
					
					consulta.add(Restrictions.eq("venda",venda));
					tipoDeVenda = (TipoDeVenda) consulta.uniqueResult();
					
					
				}catch(RuntimeException erro){
					throw erro;//display error message
				}finally {
					sessao.close();//libera recursos 
				}
			
				return tipoDeVenda;
		}

	//Operação de listar para as entidades
	@SuppressWarnings("unchecked")
	public List listarPorData(String tipo,Date dataInicio, Date dataFim){

		if(tipo.equals("Todos") || tipo.equals("")){
				
				try{
					
					//captura os critérios de consulta
					Criteria consulta = sessao.createCriteria(TipoDeVenda.class);
					consulta.createAlias("venda", "v");
					
					//consulta.add(Restrictions.eq("tipo", tipo));// tipo de venda
					
					consulta.add(Restrictions.ge("v.horario",dataInicio));// coloca restrições de busca
				     
				    consulta.add(Restrictions.le("v.horario",dataFim));
					
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
		else{
				try{
					
					//captura os critérios de consulta
					Criteria consulta = sessao.createCriteria(TipoDeVenda.class);
					consulta.createAlias("venda", "v");
					
					consulta.add(Restrictions.eq("tipo", tipo));// tipo de venda
					
					consulta.add(Restrictions.ge("v.horario",dataInicio));// coloca restrições de busca
				     
				    consulta.add(Restrictions.le("v.horario",dataFim));
					
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

}
