package com.ciadainformatica.vendas.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.ciadainformatica.vendas.dao.CidadeDAO;
import com.ciadainformatica.vendas.dao.PessoaDAO;
import com.ciadainformatica.vendas.dao.UsuarioDAO;
import com.ciadainformatica.vendas.domain.Cidade;
import com.ciadainformatica.vendas.domain.Pessoa;
import com.ciadainformatica.vendas.domain.Usuario;





@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable{

	private Pessoa pessoa;
	
	private List<Pessoa> pessoas;
	private List<Cidade> cidades;
	

	
	public Pessoa getpessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


	public List<Pessoa> getpessoas() {
		return pessoas;
	}
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}
	
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}


	
	//preenche uma lista com registro de estados
	@PostConstruct // essa anotation diz que o metodo tem que disparar no momento em que a tela é criada 
	public void listar(){
		try{
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as pessoas");
			erro.printStackTrace();
		}
	}
	
	
	public void novo(){
		
		
		try{
			pessoa = new Pessoa();
			CidadeDAO cidadesDAO = new CidadeDAO(); 
			cidades = cidadesDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao gerar nova pessoa");
			erro.printStackTrace();
		}
		
	}
	
	public void excluir(ActionEvent evento){
		try{
				Pessoa pessoa =(Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
				Messages.addGlobalError(pessoa.getNome()+" exlcuido com sucesso!");
				
				PessoaDAO pessoaDAO = new PessoaDAO();
				pessoaDAO.excluir(pessoa);
				
				novo();
		
				listar();
				
			}catch(Exception erro){
				Messages.addGlobalError("Ouve um erro na hora de apagar o usuario");
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
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.merge(pessoa);
			
			Messages.addGlobalInfo("salvo com sucesso!");
			
			novo();
			
			pessoas = pessoaDAO.listar();
			
		}catch(Exception erro){
			Messages.addGlobalError("Ouve um erro na hora de salvar a pessoa");
			erro.printStackTrace();
		}
	}
}