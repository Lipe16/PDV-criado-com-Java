package com.ciadainformatica.vendas.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.omnifaces.util.Messages;

import com.ciadainformatica.vendas.domain.Dinheiro;
import com.ciadainformatica.vendas.domain.Produto;
import com.ciadainformatica.vendas.domain.Venda;
import com.ciadainformatica.vendas.util.HibernateUtil;

public class DinheiroDAO extends GenericDAO<Dinheiro>{
	Dinheiro dinheiro = new Dinheiro();

	Transaction transacao = null;//inicia dado to tipo transação
	List<Dinheiro> auxList;
	Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
	public void atualizar(BigDecimal valor){
		
			System.out.println("entrou aqui");
			
			try{
				//captura os critérios de consulta
				Criteria consulta = sessao.createCriteria(Dinheiro.class);
				consulta.add(Restrictions.idEq(1l));// coloca restrições de busca
				 
				auxList = consulta.list();
				
				
				System.out.println("busca");
				
	
			}catch(RuntimeException erro){
				throw erro;//display error message
			}finally {
				sessao.close();//libera recursos 
			}	
			
			
			
			
			System.out.println("saiu da busca");
			
					
			
			if(auxList.isEmpty()){
				dinheiro = new Dinheiro();
				dinheiro.setDinheiro(new BigDecimal("0"));
				dinheiro.setCodigo(1L);
			
				System.out.println("esta vazio");
				// abre uma secao da fabrica de seções  e a captura
				Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
				Transaction transacao = null;//inicia dado do tipo transação
				
				try{
					
						
						dinheiro.setDinheiro(dinheiro.getDinheiro().add(valor));
						System.out.println(dinheiro.getDinheiro());
						// esse transação é o pivor a ser usado para ter certeza que a transação terá sucesso
						transacao = sessao.beginTransaction();//captura a transação pela sessao aberta
						sessao.merge(dinheiro);//atualiza entidade
						transacao.commit();//confirma transação
						
				}catch(RuntimeException erro){
					if(transacao != null){
						transacao.rollback(); // desfaz auterações
					}
					throw erro;//display error message
				}finally {
					sessao.close();//libera recursos
				}
				
				
				
				
				
			}else{
				
						dinheiro =  auxList.get(0);
					
						System.out.println("esta vazio");
						// abre uma secao da fabrica de seções  e a captura
						Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
						Transaction transacao = null;//inicia dado do tipo transação
						
						try{
							
								
								dinheiro.setDinheiro(dinheiro.getDinheiro().add(valor));
								System.out.println(dinheiro.getDinheiro());
								// esse transação é o pivor a ser usado para ter certeza que a transação terá sucesso
								transacao = sessao.beginTransaction();//captura a transação pela sessao aberta
								sessao.merge(dinheiro);//atualiza entidade
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
	
	

	

}
