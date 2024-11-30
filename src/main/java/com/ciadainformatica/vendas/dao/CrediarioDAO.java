package com.ciadainformatica.vendas.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.ciadainformatica.vendas.domain.Crediario;
import com.ciadainformatica.vendas.domain.Usuario;
import com.ciadainformatica.vendas.util.HibernateUtil;

public class CrediarioDAO extends GenericDAO<Crediario>{
	Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
	
	
	
	public List<Crediario> pesquisar(long codigo){	
		try{
			Criteria consulta = sessao.createCriteria(Crediario.class);
			
			consulta.createAlias("cliente", "c");
			consulta.add(Restrictions.eq("c.codigo", codigo));
			
			
			List<Crediario> resultado =  consulta.list();
			
			return resultado;
			
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
		
	}
	
	public List<Crediario>buscarPorCodigoDeVenda(long codigo){
		try{
			Criteria consulta = sessao.createCriteria(Crediario.class);
			
			consulta.createAlias("venda", "v");
			consulta.add(Restrictions.eq("v.codigo", codigo));
			
			
			List<Crediario> resultado =  consulta.list();
			
			return resultado;
			
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	

	
	


}
