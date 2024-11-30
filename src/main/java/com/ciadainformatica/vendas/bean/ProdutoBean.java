package com.ciadainformatica.vendas.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.ciadainformatica.vendas.dao.ClienteDAO;
import com.ciadainformatica.vendas.dao.FabricanteDAO;
import com.ciadainformatica.vendas.dao.ProdutoDAO;
import com.ciadainformatica.vendas.domain.Cliente;
import com.ciadainformatica.vendas.domain.Fabricante;
import com.ciadainformatica.vendas.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable{
	private Produto produto;
	private BigDecimal preco;
	
	private List<Produto> produtos;
	private List<Fabricante> fabricantes;
	public short quantidade;

	
	
	
	public BigDecimal getPreco() {
		return preco;
	}


	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}


	public short getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(short quantidade) {
		this.quantidade = quantidade;
	}


	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public List<Produto> getProdutos() {
		return produtos;
	}


	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
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
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os produtos");
			erro.printStackTrace();
		}
	}
	
	
	public void novo(){
		
		
		try{
			produto = new Produto();
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao gerar novo produto");
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

			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.merge(produto);
			
			Messages.addGlobalInfo("salvo com sucesso!");
			
			novo();
			
			produtos = produtoDAO.listar();
			
		}catch(Exception erro){
			Messages.addGlobalError("Ouve um erro na hora de salvar o Produto");
			erro.printStackTrace();
		}
	}
	
	
	public void adicionaValor(ActionEvent evento){
		try{
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
		}catch(Exception erro){
			Messages.addGlobalError("Ouve um erro na hora de adicionar ao estoque");
			erro.printStackTrace();
		}
	}
	
	
	public void adicionaProdutoParaEdicao(ActionEvent evento){
		try{
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado02");
		}catch(Exception erro){
			Messages.addGlobalError("Ouve um erro");
			erro.printStackTrace();
		}
	}
			
	public void adicionarEstoque(){
		try{
			//produto =(Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			
			Messages.addGlobalInfo(produto.getDescricao());
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produto = produtoDAO.buscar(produto.getCodigo());
			System.out.println(quantidade);
			
			quantidade = (short) (quantidade + produto.getQuantidade());
			
			
			
			produto.setQuantidade(quantidade);
			
			
			produtoDAO.merge(produto);
			
			
			
			novo();	
			produto =  new Produto();
			quantidade = 0;
			produtos = produtoDAO.listar();
		}catch(Exception erro){
			Messages.addGlobalError("Ouve um erro na hora de adicionar ao estoque");
			erro.printStackTrace();
		}

	
	}
	
	
	public void editarPreco(){
		try{
			//produto =(Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			
			Messages.addGlobalInfo(produto.getDescricao());
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
				
			
			
			
			produtoDAO.merge(produto);
			
			
			
			novo();	
			produto =  new Produto();
			quantidade = 0;
			produtos = produtoDAO.listar();
		}catch(Exception erro){
			Messages.addGlobalError("Ouve um erro na hora de adicionar ao estoque");
			erro.printStackTrace();
		}

	
	}
	
	
}
