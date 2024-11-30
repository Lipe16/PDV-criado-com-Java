package com.ciadainformatica.vendas.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.ciadainformatica.vendas.domain.Usuario;
import com.ciadainformatica.vendas.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario>{
	
	Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
	
	
	public Usuario autenticar(String cpf, String senha) {
		try{
			
			Criteria consulta = sessao.createCriteria(Usuario.class);
			
			consulta.createAlias("pessoa", "p");
			
			consulta.add(Restrictions.eq("p.cpf", cpf));
			consulta.add(Restrictions.eq("senha", senha));
			
			Usuario resultado = (Usuario) consulta.uniqueResult();
			
			return resultado;
			
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
}
