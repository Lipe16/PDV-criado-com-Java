package com.ciadainformatica.vendas.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import com.ciadainformatica.vendas.dao.FabricanteDAO;
import com.ciadainformatica.vendas.domain.Fabricante;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FabricanteBean implements Serializable{
	private Fabricante fabricante;
	private List<Fabricante> fabricantes;

	


	public Fabricante getFabricante() {
		return fabricante;
	}


	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}


	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}


	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}


	//preenche uma lista com registro de estados
	@PostConstruct // essa anotation diz que o metodo tem que disparar no momento em que a tela é criada 
	public void listar(){
		try{
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os fabricantes");
			erro.printStackTrace();
		}
	}
	
	
	public void novo(){
		fabricante = new Fabricante();
	}
	
	
	public void salvar(){
		//criando mensagem no braço com jsf
		/*
		String texto = "programação web com java!";
		FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, texto, texto);
		FacesContext contexto =  FacesContext.getCurrentInstance();
		contexto.addMessage(null, mensagem);
		*/

		try{
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.merge(fabricante);
			
			Messages.addGlobalInfo("salvo com sucesso!");
			
			novo();
			fabricantes = fabricanteDAO.listar();
			
		}catch(Exception erro){
			Messages.addGlobalError("Ouve um erro na hora de salvar o Estado");
			erro.printStackTrace();
		}
	}
}
