package com.ciadainformatica.vendas.util;

import org.hibernate.Session;
import org.junit.Test;

import com.ciadainformatica.vendas.util.HibernateUtil;



public class HibernateUtilTest {
	
	@Test
	public void conectar(){
		/*
		 * Metodo da classe HibernateUtil retorna uma fabrica de sessões
		 * que é aberta pelo OpenSession e essa abertura de sessao é
		 * armazenada no objeto sessao
		 */
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		// encerra abertura de sessao
		sessao.close();
		
		// enxerra abertura de sessões
		HibernateUtil.getFabricaDeSessoes().close();
	}

}
