package com.ciadainformatica.vendas.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.ciadainformatica.vendas.domain.Produto;
import com.ciadainformatica.vendas.util.HibernateUtil;

public class ProdutoDAO extends GenericDAO<Produto>{
	
	
	
	//buscar no BD
	public List<Produto> pesquisar(Produto produto){
		// abre uma secao da fabrica de seções  e a captura
		Session sessao  = HibernateUtil.getFabricaDeSessoes().openSession();
	
		try{
			//captura os critérios de consulta
			Criteria consulta = sessao.createCriteria(Produto.class);
			
			consulta.add(Restrictions.or(
				      Restrictions.eq("codigo",produto.getCodigo()),
				      Restrictions.eq("codBarras",produto.getCodBarras()))
				  );// coloca restrições de busca
			
			// joga resultado num objeto do tipo Entidade
			List<Produto> resultado = consulta.list();
			
			// retorna o resultado
			return resultado;
		}catch(RuntimeException erro){
			throw erro;//display error message
		}finally {
			sessao.close();//libera recursos 
		}	
	}

}
