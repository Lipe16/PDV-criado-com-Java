package com.ciadainformatica.vendas.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.ciadainformatica.vendas.dao.FuncionarioDAO;
import com.ciadainformatica.vendas.dao.PessoaDAO;
import com.ciadainformatica.vendas.domain.Funcionario;
import com.ciadainformatica.vendas.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FuncionarioBean implements Serializable{
	private Funcionario funcionario;
	private List<Funcionario> funcionarios;
	private List<Pessoa> pessoas;

	

	


	public Funcionario getFuncionario() {
		return funcionario;
	}


	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}


	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}


	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}


	public List<Pessoa> getPessoas() {
		return pessoas;
	}


	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}


	//preenche uma lista com registro de estados
	@PostConstruct // essa anotation diz que o metodo tem que disparar no momento em que a tela é criada 
	public void listar(){
		try{
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os funcionarios");
			erro.printStackTrace();
		}
	}
	
	
	public void novo(){
		try{
			funcionario = new Funcionario();
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
			
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
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			
			funcionarioDAO.merge(funcionario);
			
			Messages.addGlobalInfo("salvo com sucesso!");
			
			novo();
			
			funcionarios = funcionarioDAO.listar();
			
			
		}catch(Exception erro){
			Messages.addGlobalError("Ouve um erro na hora de salvar o funcionario");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try{
				funcionario =(Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");
				Messages.addGlobalError(funcionario.getPessoa().getNome()+" exlcuido com sucesso!");
				
				FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
				funcionarioDAO.excluir(funcionario);
				
				novo();
		
				funcionarios = funcionarioDAO.listar();
			}catch(Exception erro){
				Messages.addGlobalError("Ouve um erro na hora de apagar o funcionario");
				erro.printStackTrace();
			}

		
	}
}
