package com.ciadainformatica.vendas.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.ciadainformatica.vendas.dao.EstadoDAO;
import com.ciadainformatica.vendas.domain.Estado;

public class EstadoDAOTest {
	@Test
	@Ignore // ignora esse metodo no JUnit
	public void salvar(){
		Estado estado = new Estado();
		estado.setNome("Rio de Janeiro");
		estado.setSigla("RJ");
		
		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
	}
	
	// testar o listar
	@Test
	@Ignore
	public void listar(){
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> resultado = estadoDAO.listar();
		
		for(Estado estado : resultado){
			System.out.println("nome: "+estado.getNome()+", sigla: "+estado.getSigla());
		}
	}
	
	@Test
	@Ignore
	//buscar pelo codigo
	public void buscar(){
		Long codigo = 2L;
		
		EstadoDAO estadoDAO =  new EstadoDAO();
		Estado resultado = estadoDAO.buscar(codigo);
		
		if(resultado ==  null){
			System.out.println(" não obteve exito na busca");
		}else{
			System.out.println(resultado.getNome());
		}
	}
	
	@Test
	@Ignore
	//excluir registro
		public void excluir(){
			Long codigo = 3L;
			
			EstadoDAO estadoDAO =  new EstadoDAO();
			Estado resultado = estadoDAO.buscar(codigo);
			
			if(resultado ==  null){
				System.out.println("não encontrou registro");
			}else{
				estadoDAO.excluir(resultado);
			}
		}
	
	@Test
	@Ignore
	//editar registro
		public void editar(){
			Long codigo = 3L;
			
			// consultar se o elemento a ser editado existe
			EstadoDAO estadoDAO =  new EstadoDAO();
			Estado estado = estadoDAO.buscar(codigo);
			
			if(estado ==  null){
				System.out.println("não encontrou registro");
			}else{
				// novos dados
				estado.setNome("Rio Grande do Sul");
				estado.setSigla("RS");
				estadoDAO.editar(estado);
			}
		}
	
}
