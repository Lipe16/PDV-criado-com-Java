package com.ciadainformatica.vendas.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.ciadainformatica.vendas.dao.CidadeDAO;
import com.ciadainformatica.vendas.dao.EstadoDAO;
import com.ciadainformatica.vendas.domain.Cidade;
import com.ciadainformatica.vendas.domain.Estado;



public class CidadeDAOTest {
	
	@Test
	@Ignore
	public void salvar(){
		Long codigo = 2L;
		
		EstadoDAO estadoDAO =  new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);
		
		if(estado ==  null){
			System.out.println(" não obteve exito na busca de estado");
		}else{
			Cidade cidade = new Cidade();
			cidade.setNome("Xérem");
			cidade.setEstado(estado);
			
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.salvar(cidade);
		}
	}
	
	@Test
	@Ignore
	public void listar(){
		CidadeDAO	cidadeDAO = new CidadeDAO();
		List<Cidade> cidades = cidadeDAO.listar();
		
		for(Cidade cidade : cidades){
			System.out.println("\n\n");
			System.out.println("codigo da cidade: "+cidade.getCodigo());
			System.out.println("nome da cidade: "+cidade.getNome());
			System.out.println("codigo do estado: "+cidade.getEstado().getCodigo());
			System.out.println("nome do estado: "+cidade.getEstado().getNome());
			System.out.println("sigla do estado: "+cidade.getEstado().getSigla());
		}
	}
	
	@Test
	@Ignore
	public void buscar(){
		Long codigo = 2L;
		CidadeDAO	cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);
		
		if(cidade == null){
			System.out.println("nenhum registro encontrado");
		}
		else{
			System.out.println("\n\n");
			System.out.println("codigo da cidade: "+cidade.getCodigo());
			System.out.println("nome da cidade: "+cidade.getNome());
			System.out.println("codigo do estado: "+cidade.getEstado().getCodigo());
			System.out.println("nome do estado: "+cidade.getEstado().getNome());
			System.out.println("sigla do estado: "+cidade.getEstado().getSigla());
		}
	}
}
