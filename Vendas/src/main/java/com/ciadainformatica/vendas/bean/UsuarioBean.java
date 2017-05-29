package com.ciadainformatica.vendas.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.ciadainformatica.vendas.dao.PessoaDAO;
import com.ciadainformatica.vendas.dao.UsuarioDAO;
import com.ciadainformatica.vendas.domain.Pessoa;
import com.ciadainformatica.vendas.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable{
	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<Pessoa> pessoas;

	

	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public List<Usuario> getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
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
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os usuarios");
			erro.printStackTrace();
		}
	}
	
	
	public void novo(){
		try{
			usuario = new Usuario();
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
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.merge(usuario);
			
			Messages.addGlobalInfo("salvo com sucesso!");
			
			novo();
			
			usuarios = usuarioDAO.listar();
			
			
		}catch(Exception erro){
			Messages.addGlobalError("Ouve um erro na hora de salvar o funcionario");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento){
		try{
				usuario =(Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
				Messages.addGlobalError(usuario.getPessoa().getNome()+" exlcuido com sucesso!");
				
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				usuarioDAO.excluir(usuario);
				
				novo();
		
				usuarios = usuarioDAO.listar();
			}catch(Exception erro){
				Messages.addGlobalError("Ouve um erro na hora de apagar o usuario");
				erro.printStackTrace();
			}
	}
}
