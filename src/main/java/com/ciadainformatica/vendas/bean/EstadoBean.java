package com.ciadainformatica.vendas.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.ciadainformatica.vendas.dao.EstadoDAO;
import com.ciadainformatica.vendas.domain.Estado;





@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable{

	private Estado estado;
	private List<Estado> estados;

	
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}


	public List<Estado> getEstados() {
		return estados;
	}
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}


	


	
	//preenche uma lista com registro de estados
	@PostConstruct // essa anotation diz que o metodo tem que disparar no momento em que a tela é criada 
	public void listar(){
		try{
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os estados");
			erro.printStackTrace();
		}
	}
	
	
	public void novo(){
		estado = new Estado();
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
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.merge(estado);
			
			Messages.addGlobalInfo("salvo com sucesso!");
			
			novo();
			estados = estadoDAO.listar();
			
		}catch(Exception erro){
			Messages.addGlobalError("Ouve um erro na hora de salvar o Estado");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try{
				estado =(Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
				Messages.addGlobalError(estado.getNome()+" exlcuido com sucesso!");
				
				EstadoDAO estadoDAO = new EstadoDAO();
				estadoDAO.excluir(estado);
				
				novo();
				estados = estadoDAO.listar();
			}catch(Exception erro){
				Messages.addGlobalError("Ouve um erro na hora de apagar o Estado");
				erro.printStackTrace();
			}

		
	}
	
}