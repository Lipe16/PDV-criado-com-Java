package com.ciadainformatica.vendas.dao;


import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.ciadainformatica.vendas.util.HibernateUtil;

public class GenericDAO<Entidade> {
	
	/**
	 * esse bloco de códogo usa metodos do Java Reflect
	 * pra especializar a classe generica a se adaptar
	 * as demais classes, para mais informações consulte
	 * a biblioteca JavaReflect
	 */
	private Class<Entidade> classe;
	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}
	//***************** fim do bloco referente ao java reflect *********
	
	
	// operação de salvar para as entidades
	public void salvar(Entidade entidade){
		// abre uma secao da fabrica de seções  e a captura
		Session sessao  = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;//inicia dado do tipo transação
		try{
			// esse transação é o pivor a ser usado para ter certeza que a inserção de dados teve sucesso
			transacao = sessao.beginTransaction();//captura a transação pela sessao aberta
			sessao.save(entidade);//salva entidade
			transacao.commit();//confirma transação
			
		}catch(RuntimeException erro){
			if(transacao != null){
				transacao.rollback(); // desfaz auterações
			}
			throw erro;//display error message
		}finally {
			sessao.close();//libera recursos
		}
	}
	
	
	
	//Operação de listar para as entidades
	@SuppressWarnings("unchecked")
	public List<Entidade> listar(){
		// abre uma secao da fabrica de seções  e a captura
		Session sessao  = HibernateUtil.getFabricaDeSessoes().openSession();
		
		
		try{
			//captura os critérios de consulta
			Criteria consulta = sessao.createCriteria(classe);
			// joga os resultados numa lista
			List<Entidade> resultado = consulta.list();
			// retorna o resultado
			return resultado;	
		}catch(RuntimeException erro){
			throw erro;//display error message
		}finally {
			sessao.close();//libera recursos 
		}	
	}
	
	
	
	//buscar no BD
	@SuppressWarnings("unchecked")
	public Entidade buscar(Long codigo){
		// abre uma secao da fabrica de seções  e a captura
		Session sessao  = HibernateUtil.getFabricaDeSessoes().openSession();
	
		try{
			//captura os critérios de consulta
			Criteria consulta = sessao.createCriteria(classe);
			consulta.add(Restrictions.idEq(codigo));// coloca restrições de busca
			
			// joga resultado num objeto do tipo Entidade
			Entidade resultado = (Entidade) consulta.uniqueResult();
			
			// retorna o resultado
			return resultado;	
		}catch(RuntimeException erro){
			throw erro;//display error message
		}finally {
			sessao.close();//libera recursos 
		}	
	}
	
	
	
	// operação de excluir
	public void excluir(Entidade entidade){
		// abre uma secao da fabrica de seções  e a captura
		Session sessao  = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;//inicia dado to tipo transação
		try{
			// esse transação é o pivor a ser usado para ter certeza que a transação terá sucesso
			transacao = sessao.beginTransaction();//captura a transação pela sessao aberta
			sessao.delete(entidade);//salva entidade
			transacao.commit();//confirma transação
			
		}catch(RuntimeException erro){
			if(transacao != null){
				transacao.rollback(); // desfaz auterações
			}
			throw erro;//display error message
		}finally {
			sessao.close();//libera recursos
		}
	}
	
	
	// operação de editar
	public void editar(Entidade entidade){
		// abre uma secao da fabrica de seções  e a captura
		Session sessao  = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;//inicia dado to tipo transação
		try{
			// esse transação é o pivor a ser usado para ter certeza que a transação terá sucesso
			transacao = sessao.beginTransaction();//captura a transação pela sessao aberta
			sessao.update(entidade);//atualiza entidade
			transacao.commit();//confirma transação
			
		}catch(RuntimeException erro){
			if(transacao != null){
				transacao.rollback(); // desfaz auterações
			}
			throw erro;//display error message
		}finally {
			sessao.close();//libera recursos
		}
	}
	
	
	// operação de salvar ou editar  as entidades, o merge
	public void merge(Entidade entidade){
		// abre uma secao da fabrica de seções  e a captura
		Session sessao  = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;//inicia dado to tipo transação
		try{
			// esse transação é o pivor a ser usado para ter certeza que a inserção de dados teve sucesso
			transacao = sessao.beginTransaction();//captura a transação pela sessao aberta
			sessao.merge(entidade);//salva entidade
			transacao.commit();//confirma transação
			
		}catch(RuntimeException erro){
			if(transacao != null){
				transacao.rollback(); // desfaz auterações
			}
			throw erro;//display error message
		}finally {
			sessao.close();//libera recursos
		}
	}
	
	
	

	
	
}
