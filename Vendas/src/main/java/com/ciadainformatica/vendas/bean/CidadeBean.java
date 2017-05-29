package com.ciadainformatica.vendas.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import com.ciadainformatica.vendas.dao.CidadeDAO;
import com.ciadainformatica.vendas.dao.EstadoDAO;
import com.ciadainformatica.vendas.domain.Cidade;
import com.ciadainformatica.vendas.domain.Estado;
import com.ciadainformatica.vendas.domain.Pessoa;





@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable{

	private Cidade cidade;
	private List<Cidade> cidades;
	private List<Estado> estados;

	
	public Cidade getCidade() {
		return cidade;
	}
	
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}


	public List<Cidade> getCidades() {
		return cidades;
	}
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
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
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os estados");
			erro.printStackTrace();
		}
	}
	
	
	public void novo(){
		try{
			cidade = new Cidade();
			EstadoDAO estadosDAO = new EstadoDAO();
			estados = estadosDAO.listar();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao gerar nova pessoa");
			erro.printStackTrace();
		}
		
		
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
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidadeDAO.merge(cidade);
			
			Messages.addGlobalInfo("salvo com sucesso!");
			
			novo();
			
			cidades = cidadeDAO.listar();
			
		}catch(Exception erro){
			Messages.addGlobalError("Ouve um erro na hora de salvar a cidade");
			erro.printStackTrace();
		}
	}
}