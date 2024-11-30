package com.ciadainformatica.vendas.bean;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.event.RowEditEvent;

import com.ciadainformatica.vendas.dao.CrediarioDAO;
import com.ciadainformatica.vendas.dao.DinheiroDAO;
import com.ciadainformatica.vendas.dao.ItemVendaDAO;
import com.ciadainformatica.vendas.dao.ProdutoDAO;
import com.ciadainformatica.vendas.dao.TipoDeVendaDAO;
import com.ciadainformatica.vendas.dao.VendaDAO;
import com.ciadainformatica.vendas.domain.Cliente;
import com.ciadainformatica.vendas.domain.Crediario;
import com.ciadainformatica.vendas.domain.Funcionario;
import com.ciadainformatica.vendas.domain.ItemVenda;
import com.ciadainformatica.vendas.domain.Produto;
import com.ciadainformatica.vendas.domain.TipoDeVenda;
import com.ciadainformatica.vendas.domain.Usuario;
import com.ciadainformatica.vendas.domain.Venda;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ItemDeVendaTrocaBean implements Serializable{
	private long codigo;
	private List<ItemVenda> itensDaVendaDaTroca, itensSelecionadosParaTroca;
	private Funcionario funcionario;
	private Venda venda;
	private Produto produto;
	private List<Produto> produtos;
	public String codigoProduto;
	private ItemVenda itemNovaVenda;
	private List<ItemVenda> itensNovaVenda;
	private short quantidade = 1;
	private BigDecimal valorTotalNovaVenda, valorTotalTroca, valorDaDiferenca, parcela, troco, dinheiroEntrada;
	private Cliente cliente;
	private int numeroDeParcelas;
	
	

	
	
	
	
	public BigDecimal getTroco() {
		return troco;
		
	}

	public void setTroco(BigDecimal troco) {
		this.troco = troco;
	}

	public BigDecimal getDinheiroEntrada() {
		return dinheiroEntrada;
	}

	public void setDinheiroEntrada(BigDecimal dinheiroEntrada) {
		this.dinheiroEntrada = dinheiroEntrada;
	}

	public BigDecimal getParcela() {
		return parcela;
	}

	public void setParcela(BigDecimal parcela) {
		this.parcela = parcela;
	}

	public int getNumeroDeParcelas() {
		return numeroDeParcelas;
	}



	public void setNumeroDeParcelas(int numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}



	public long getCodigo() {
		return codigo;
	}



	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}



	public List<ItemVenda> getItensDaVendaDaTroca() {
		return itensDaVendaDaTroca;
	}



	public void setItensDaVendaDaTroca(List<ItemVenda> itensDaVendaDaTroca) {
		this.itensDaVendaDaTroca = itensDaVendaDaTroca;
	}



	public List<ItemVenda> getItensSelecionadosParaTroca() {
		return itensSelecionadosParaTroca;
	}



	public void setItensSelecionadosParaTroca(List<ItemVenda> itensSelecionadosParaTroca) {
		this.itensSelecionadosParaTroca = itensSelecionadosParaTroca;
	}



	public Funcionario getFuncionario() {
		return funcionario;
	}



	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}



	public Venda getVenda() {
		return venda;
	}



	public void setVenda(Venda venda) {
		this.venda = venda;
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



	public String getCodigoProduto() {
		return codigoProduto;
	}



	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}



	public ItemVenda getItemNovaVenda() {
		return itemNovaVenda;
	}



	public void setItemNovaVenda(ItemVenda itemNovaVenda) {
		this.itemNovaVenda = itemNovaVenda;
	}



	public List<ItemVenda> getItensNovaVenda() {
		return itensNovaVenda;
	}



	public void setItensNovaVenda(List<ItemVenda> itensNovaVenda) {
		this.itensNovaVenda = itensNovaVenda;
	}



	public short getQuantidade() {
		return quantidade;
	}



	public void setQuantidade(short quantidade) {
		this.quantidade = quantidade;
	}



	public BigDecimal getValorTotalNovaVenda() {
		return valorTotalNovaVenda;
	}



	public void setValorTotalNovaVenda(BigDecimal valorTotalNovaVenda) {
		this.valorTotalNovaVenda = valorTotalNovaVenda;
	}



	public BigDecimal getValorTotalTroca() {
		return valorTotalTroca;
	}



	public void setValorTotalTroca(BigDecimal valorTotalTroca) {
		this.valorTotalTroca = valorTotalTroca;
	}



	public BigDecimal getValorDaDiferenca() {
		return valorDaDiferenca;
	}



	public void setValorDaDiferenca(BigDecimal valorDaDiferenca) {
		this.valorDaDiferenca = valorDaDiferenca;
	}



	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	//preenche uma lista com registro de estados
	@PostConstruct // essa anotation diz que o metodo tem que disparar no momento em que a tela é criada 
	public void listar(){
		itensDaVendaDaTroca = null;
		itensSelecionadosParaTroca = null;

		numeroDeParcelas = 1;
		itensNovaVenda = new ArrayList<ItemVenda>();
		itemNovaVenda = new ItemVenda();
		produto = new Produto();
		funcionario = new Funcionario();
		cliente = new Cliente();
		venda = new Venda();
		valorDaDiferenca = new BigDecimal(0);
		funcionario = new Funcionario();
		cliente = new Cliente();

		

	}
	
	
	
	public void buscarItensDaVenda(){
		itensDaVendaDaTroca = null;
		
		ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
		itensDaVendaDaTroca = itemVendaDAO.buscarPorCodigoDeVenda(codigo);
	}
	
	
	
	
public void adicionarItem(){
	
		if(funcionario != null && funcionario.getCodigo().toString() != ""){
			
			produtos = new ArrayList<Produto>();
			produto = new Produto();
			if(codigoProduto == null || codigoProduto.trim().equals("")){
				Messages.addGlobalError("A pesquisa está vazia");
			}else{
				
				//capturando dados para pesquisa
				try{
				    produto.setCodigo(Long.parseLong(codigoProduto));
				    produto.setCodBarras(codigoProduto);
				}catch(NumberFormatException e){
					//System.out.println(codigo);
					produto.setCodBarras(codigoProduto);
				}
				
				try{
					codigoProduto = "";
					ProdutoDAO produtoDAO = new ProdutoDAO();
					produtos = produtoDAO.pesquisar(produto);
					
				}catch(Exception erro){
					Messages.addGlobalError("Nenhum produto encontrado!");
				}
				
			//-----	
				if(produtos.size() == 1){//quando produto é encontrado
					itemNovaVenda.setProduto(produtos.get(0));
					itemNovaVenda.setFuncionario(funcionario);
					
					
					if(quantidade > 0){
						itemNovaVenda.setQuantidade(quantidade);					
						quantidade = 1;
					}else{
						Messages.addGlobalError("não é permitido quantidade menor que 1, logo foi atribuido 1 a quantidade!");
						quantidade = 1;
						itemNovaVenda.setQuantidade(quantidade);
					}
					
					for(int i = 0; i<itensNovaVenda.size(); i++){
						if(itemNovaVenda.getProduto().getCodigo() == itensNovaVenda.get(i).getProduto().getCodigo()){
							quantidade = (short) (itemNovaVenda.getQuantidade() + itensNovaVenda.get(i).getQuantidade());
							itemNovaVenda.setQuantidade(quantidade);
							itensNovaVenda.remove(i);
							quantidade = 1;
						}
					}
					itemNovaVenda.setValorParcial(itemNovaVenda.getProduto().getPreco().multiply(new BigDecimal(itemNovaVenda.getQuantidade())));
					//até a linha anterior o item já armazenou os valores que necessita menos venda
					
					itensNovaVenda.add(0,itemNovaVenda);	
					itemNovaVenda = new ItemVenda();
					
					valorTotalNovaVenda();
					
					
					
					
				}else if(produtos.size() == 0){
					Messages.addGlobalError("Nenhum produto encontrado!");
				}else{
					Messages.addGlobalError("Erro!");
				}
			}
		}else{
			Messages.addGlobalError("Adicione um funcionario para a venda!");
		}
	}



public void excluir(ActionEvent evento){
	try{
			itemNovaVenda =(ItemVenda) evento.getComponent().getAttributes().get("itemSelecionado");
			
			
			for(int i = 0; i<itensNovaVenda.size(); i++){
				if(itemNovaVenda.getProduto().getCodigo() == itensNovaVenda.get(i).getProduto().getCodigo()){
					itensNovaVenda.remove(i);
					Messages.addGlobalError(itemNovaVenda.getProduto().getDescricao()+" exlcuido com sucesso!");
					valorTotalNovaVenda();
				}
			}
			

		}catch(Exception erro){
			Messages.addGlobalError("Ouve um erro na hora de apagar");
			erro.printStackTrace();
		}
}


public void editarValorDoItem(RowEditEvent event){
	itemNovaVenda = new ItemVenda();
	try{
		itemNovaVenda =(ItemVenda) event.getObject();
				
				
				AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
				
				Usuario usuario = autenticacaoBean.getUsuarioLogado();
				
				if(usuario.getTipo() == 'A' || usuario.getTipo() == 'G'){
							System.out.println(itemNovaVenda.getQuantidade());
							itemNovaVenda.setValorParcial(itemNovaVenda.getProduto().getPreco().multiply(new BigDecimal(itemNovaVenda.getQuantidade())));
							
							for(int i = 0; i<itensNovaVenda.size(); i++){
								if(itemNovaVenda.getProduto().getCodigo() == itensDaVendaDaTroca.get(i).getProduto().getCodigo()){
									
									
									itensNovaVenda.set(i, itemNovaVenda);
								
									
								
									Messages.addGlobalInfo(itemNovaVenda.getProduto().getDescricao()+" preco alterado com sucesso!");
								
						}
					}
				
					itemNovaVenda = new ItemVenda();
					valorTotalNovaVenda();
				}else{
					itemNovaVenda = new ItemVenda();
					Messages.addGlobalError("Usuario não autorizado!");
				}
		}catch(Exception erro){
			Messages.addGlobalError("Ouve um erro na hora de editar valor");
			erro.printStackTrace();
		}

}

public void cancelarValorDoItem(RowEditEvent event){
	itemNovaVenda =(ItemVenda) event.getObject();
	Messages.addGlobalError(itemNovaVenda.getProduto().getDescricao()+" com preço cancelado!");
}




	
public void valorTotalNovaVenda(){
	valorTotalNovaVenda = new BigDecimal(0);	
	for(int i = 0; i < itensNovaVenda.size(); i++){
		
		valorTotalNovaVenda = valorTotalNovaVenda.add(itensNovaVenda.get(i).getValorParcial());	
	}

}


public void valorTotalTroca(){
	venda.setCliente(itensSelecionadosParaTroca.get(0).getVenda().getCliente());
	cliente = itensSelecionadosParaTroca.get(0).getVenda().getCliente();
	
	
	valorTotalTroca = new BigDecimal(0);	
	for(int i = 0; i < itensSelecionadosParaTroca.size(); i++){
		
		valorTotalTroca = valorTotalTroca.add(itensSelecionadosParaTroca.get(i).getValorParcial());	
	}

}

public void calcularParcelas(){
	
	if(numeroDeParcelas >= 1){
		try{
			parcela = valorDaDiferenca.divide(new BigDecimal(numeroDeParcelas));
		}catch(Exception erro){
			numeroDeParcelas = 1;
			Messages.addGlobalError("ocorreu um erro a calcular as parcelas");
		}
	}else{
		numeroDeParcelas = 1;
	}
}

public void calcularTroco(){
	try{
		if(dinheiroEntrada.doubleValue() > 0 && dinheiroEntrada != null){
			
				troco = dinheiroEntrada.subtract(valorDaDiferenca);
	
		}
	}catch(Exception erro){
		Messages.addGlobalError("ocorreu um erro a calcular o troco");
	}
	
	if(valorDaDiferenca.doubleValue() < 0){
		troco = new BigDecimal(0);
	}
}



public void valorDaDiferenca(){

	
	valorTotalTroca();
	valorTotalNovaVenda();
	
	valorDaDiferenca = new BigDecimal(0);
	valorDaDiferenca = valorTotalNovaVenda.subtract(valorTotalTroca);
}




// trocar pagando diferença em dinheiro
public void trocarComDiferencaEmDinheiro(){
	if(itensSelecionadosParaTroca.size() > 0){
		if(valorDaDiferenca.doubleValue() >= 0){
			
			
				try{	
									
						VendaDAO vendaDAO = new VendaDAO();
						ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
						ProdutoDAO produtoDAO = new ProdutoDAO();
						venda = new Venda();
						Calendar dataAtual = Calendar.getInstance();							
						TipoDeVenda tipoDeVenda = new TipoDeVenda();
						TipoDeVendaDAO tipoDeVendaDAO = new TipoDeVendaDAO();		
						DinheiroDAO dinheiroDAO = new DinheiroDAO();
											
						venda = itensSelecionadosParaTroca.get(0).getVenda();
						venda.setValorTotal(venda.getValorTotal().subtract(valorTotalTroca));
						vendaDAO.merge(venda);
						
			
						
		
						for(int i=0; i < itensSelecionadosParaTroca.size(); i++){
						
								itemVendaDAO.excluir(itensSelecionadosParaTroca.get(i));
				
							
								produto = produtoDAO.buscar(itensSelecionadosParaTroca.get(i).getProduto().getCodigo());
								produto.setQuantidade((short) (produto.getQuantidade() + itensSelecionadosParaTroca.get(i).getQuantidade()));
								produtoDAO.merge(produto);

							
						}
						
						
						venda = new Venda();
						
						itensSelecionadosParaTroca.clear();
						

						//---- segunda parte
		

							
							if(valorDaDiferenca.doubleValue() > 0){
										
								
								
									
									venda.setValorTotal(valorTotalNovaVenda);
									venda.setHorario(dataAtual.getTime());
									venda.setCliente(cliente);
									vendaDAO.salvar(venda);
									
									tipoDeVenda.setTipo("Troca + Dinheiro");
									tipoDeVenda.setVenda(venda);
									tipoDeVendaDAO.merge(tipoDeVenda);
									
									dinheiroDAO.atualizar(valorDaDiferenca);// atualiza dinheiro do sistema
									
									
									for(int i = 0; i < itensNovaVenda.size(); i++){
										
										itemNovaVenda = itensNovaVenda.get(i);
										itemNovaVenda.setVenda(venda);
										itemVendaDAO.salvar(itemNovaVenda);
										
										//atualizar estoque
										produto = produtoDAO.buscar(itemNovaVenda.getProduto().getCodigo());
										short aux = (short) (produto.getQuantidade()- itemNovaVenda.getQuantidade());
										produto.setQuantidade(aux);
										produtoDAO.merge(produto);
										
										produto = new Produto();
										aux = 0;
															
									}
						
									tipoDeVenda = new TipoDeVenda();
									valorDaDiferenca = new BigDecimal(0);
									itemNovaVenda = new ItemVenda();
									funcionario = new Funcionario();
									cliente = new Cliente();
									itensNovaVenda.clear();	
									itensDaVendaDaTroca.clear();
									itensSelecionadosParaTroca.clear();
									itensNovaVenda.clear();
									
									Messages.addFlashGlobalInfo("TROCA REALIZADA COM SUCESSO!");
									
		
							}else if(valorDaDiferenca.doubleValue()  == 0){
								
								tipoDeVenda = new TipoDeVenda();
								valorDaDiferenca = new BigDecimal(0);
								itemNovaVenda = new ItemVenda();
								funcionario = new Funcionario();
								cliente = new Cliente();
								itensNovaVenda.clear();	
								itensDaVendaDaTroca.clear();
								itensSelecionadosParaTroca.clear();
								itensNovaVenda.clear();
								Messages.addFlashGlobalInfo("TROCA REALIZADA COM SUCESSO!");
							}
				
				}catch (Exception e) {
						Messages.addGlobalError("Ouve um erro dentro do processo de troca, favor checar a troca");
						e.printStackTrace();
				}
				
		}else{
			Messages.addGlobalError("Ouve um erro, os produtos selecionados tem um valor inferior a troca");
		}
	}else{
		Messages.addGlobalError("Ouve um erro, nenhum item de troca selecionado!");
	}
	
}



//trocar pagando diferença cartao D
public void trocarComDiferencaEmCD(){

	if(itensSelecionadosParaTroca.size() > 0){
		if(valorDaDiferenca.doubleValue() >= 0){

			
				try{	
									
						VendaDAO vendaDAO = new VendaDAO();
						ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
						ProdutoDAO produtoDAO = new ProdutoDAO();
						venda = new Venda();
						Calendar dataAtual = Calendar.getInstance();							
						TipoDeVenda tipoDeVenda = new TipoDeVenda();
						TipoDeVendaDAO tipoDeVendaDAO = new TipoDeVendaDAO();		
						
											
						venda = itensSelecionadosParaTroca.get(0).getVenda();
						venda.setValorTotal(venda.getValorTotal().subtract(valorTotalTroca));
						vendaDAO.merge(venda);
						
			
						
		
						for(int i=0; i < itensSelecionadosParaTroca.size(); i++){
						
								itemVendaDAO.excluir(itensSelecionadosParaTroca.get(i));
				
							
								produto = produtoDAO.buscar(itensSelecionadosParaTroca.get(i).getProduto().getCodigo());
								produto.setQuantidade((short) (produto.getQuantidade() + itensSelecionadosParaTroca.get(i).getQuantidade()));
								produtoDAO.merge(produto);

							
						}
						
						
						venda = new Venda();
						itensSelecionadosParaTroca.clear();
						

						//---- segunda parte
		

							
							if(valorDaDiferenca.doubleValue() > 0){
										
								
								
									
									venda.setValorTotal(valorTotalNovaVenda);
									venda.setHorario(dataAtual.getTime());
									venda.setCliente(cliente);
									vendaDAO.salvar(venda);
									
									tipoDeVenda.setTipo("Troca + Cartão de Débito");
									tipoDeVenda.setVenda(venda);
									tipoDeVendaDAO.merge(tipoDeVenda);
									
									
									for(int i = 0; i < itensNovaVenda.size(); i++){
										
										itemNovaVenda = itensNovaVenda.get(i);
										itemNovaVenda.setVenda(venda);
										itemVendaDAO.salvar(itemNovaVenda);
										
										//atualizar estoque
										produto = produtoDAO.buscar(itemNovaVenda.getProduto().getCodigo());
										short aux = (short) (produto.getQuantidade()- itemNovaVenda.getQuantidade());
										produto.setQuantidade(aux);
										produtoDAO.merge(produto);
										
										produto = new Produto();
										aux = 0;
															
									}
						
									tipoDeVenda = new TipoDeVenda();
									valorDaDiferenca = new BigDecimal(0);
									itemNovaVenda = new ItemVenda();
									funcionario = new Funcionario();
									cliente = new Cliente();
									itensNovaVenda.clear();	
									itensDaVendaDaTroca.clear();
									itensSelecionadosParaTroca.clear();
									itensNovaVenda.clear();
									
									Messages.addFlashGlobalInfo("TROCA REALIZADA COM SUCESSO!");
									
		
							}else if(valorDaDiferenca.doubleValue()  == 0){
								
								tipoDeVenda = new TipoDeVenda();
								valorDaDiferenca = new BigDecimal(0);
								itemNovaVenda = new ItemVenda();
								funcionario = new Funcionario();
								cliente = new Cliente();
								itensNovaVenda.clear();	
								itensDaVendaDaTroca.clear();
								itensSelecionadosParaTroca.clear();
								itensNovaVenda.clear();
								Messages.addFlashGlobalInfo("TROCA REALIZADA COM SUCESSO!");
							}
				
				}catch (Exception e) {
						Messages.addGlobalError("Ouve um erro dentro do processo de troca, favor checar a troca");
						e.printStackTrace();
				}
				
		}else{
			Messages.addGlobalError("Ouve um erro, os produtos selecionados tem um valor inferior a troca");
		}
	}else{
		Messages.addGlobalError("Ouve um erro, nenhum item de troca selecionado!");
	}
	
}


//trocar pagando diferença cartao Credito
public void trocarComDiferencaEmCC(){

	if(itensSelecionadosParaTroca.size() > 0){
		if(valorDaDiferenca.doubleValue() >= 0){
			

			
				try{	
									
						VendaDAO vendaDAO = new VendaDAO();
						ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
						ProdutoDAO produtoDAO = new ProdutoDAO();
						venda = new Venda();
						Calendar dataAtual = Calendar.getInstance();							
						TipoDeVenda tipoDeVenda = new TipoDeVenda();
						TipoDeVendaDAO tipoDeVendaDAO = new TipoDeVendaDAO();		
						
											
						venda = itensSelecionadosParaTroca.get(0).getVenda();
						venda.setValorTotal(venda.getValorTotal().subtract(valorTotalTroca));
						vendaDAO.merge(venda);
						
			
						
		
						for(int i=0; i < itensSelecionadosParaTroca.size(); i++){
						
								itemVendaDAO.excluir(itensSelecionadosParaTroca.get(i));
				
							
								produto = produtoDAO.buscar(itensSelecionadosParaTroca.get(i).getProduto().getCodigo());
								produto.setQuantidade((short) (produto.getQuantidade() + itensSelecionadosParaTroca.get(i).getQuantidade()));
								produtoDAO.merge(produto);

							
						}
						
						
						venda = new Venda();
						itensSelecionadosParaTroca.clear();
						

						//---- segunda parte
		

							
							if(valorDaDiferenca.doubleValue() > 0){
										
								
								
									
									venda.setValorTotal(valorTotalNovaVenda);
									venda.setHorario(dataAtual.getTime());
									venda.setCliente(cliente);
									vendaDAO.salvar(venda);
									
									tipoDeVenda.setTipo("Troca + Cartão de Crédito");
									tipoDeVenda.setVenda(venda);
									tipoDeVendaDAO.merge(tipoDeVenda);
									
									
									for(int i = 0; i < itensNovaVenda.size(); i++){
										
										itemNovaVenda = itensNovaVenda.get(i);
										itemNovaVenda.setVenda(venda);
										itemVendaDAO.salvar(itemNovaVenda);
										
										//atualizar estoque
										produto = produtoDAO.buscar(itemNovaVenda.getProduto().getCodigo());
										short aux = (short) (produto.getQuantidade()- itemNovaVenda.getQuantidade());
										produto.setQuantidade(aux);
										produtoDAO.merge(produto);
										
										produto = new Produto();
										aux = 0;
															
									}
						
									tipoDeVenda = new TipoDeVenda();
									valorDaDiferenca = new BigDecimal(0);
									itemNovaVenda = new ItemVenda();
									funcionario = new Funcionario();
									cliente = new Cliente();
									itensNovaVenda.clear();	
									itensDaVendaDaTroca.clear();
									itensSelecionadosParaTroca.clear();
									itensNovaVenda.clear();
									
									Messages.addFlashGlobalInfo("TROCA REALIZADA COM SUCESSO!");
									
		
							}else if(valorDaDiferenca.doubleValue()  == 0){
								
								tipoDeVenda = new TipoDeVenda();
								valorDaDiferenca = new BigDecimal(0);
								itemNovaVenda = new ItemVenda();
								funcionario = new Funcionario();
								cliente = new Cliente();
								itensNovaVenda.clear();	
								itensDaVendaDaTroca.clear();
								itensSelecionadosParaTroca.clear();
								itensNovaVenda.clear();
								Messages.addFlashGlobalInfo("TROCA REALIZADA COM SUCESSO!");
							}
				
				}catch (Exception e) {
						Messages.addGlobalError("Ouve um erro dentro do processo de troca, favor checar a troca");
						e.printStackTrace();
				}
				
		}else{
			Messages.addGlobalError("Ouve um erro, os produtos selecionados tem um valor inferior a troca");
		}
	}else{
		Messages.addGlobalError("Ouve um erro, nenhum item de troca selecionado!");
	}
	
}


//trocar pagando diferença cartao Crediario
public void trocarComDiferencaEmC(){

	if(itensSelecionadosParaTroca.size() > 0){
		if(valorDaDiferenca.doubleValue() >= 0  && numeroDeParcelas > 0){
			
			
				try{	
									
						VendaDAO vendaDAO = new VendaDAO();
						ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
						ProdutoDAO produtoDAO = new ProdutoDAO();
						venda = new Venda();
						Calendar dataAtual = Calendar.getInstance();							
						TipoDeVenda tipoDeVenda = new TipoDeVenda();
						TipoDeVendaDAO tipoDeVendaDAO = new TipoDeVendaDAO();		
						
											
						venda = itensSelecionadosParaTroca.get(0).getVenda();
						venda.setValorTotal(venda.getValorTotal().subtract(valorTotalTroca));
						vendaDAO.merge(venda);
						
			
						
		
						for(int i=0; i < itensSelecionadosParaTroca.size(); i++){
						
								itemVendaDAO.excluir(itensSelecionadosParaTroca.get(i));
				
							
								produto = produtoDAO.buscar(itensSelecionadosParaTroca.get(i).getProduto().getCodigo());
								produto.setQuantidade((short) (produto.getQuantidade() + itensSelecionadosParaTroca.get(i).getQuantidade()));
								produtoDAO.merge(produto);

							
						}
						
						
						venda = new Venda();
						itensSelecionadosParaTroca.clear();
						

						//---- segunda parte
		

							
							if(valorDaDiferenca.doubleValue() > 0){
								if(cliente != null){			
											Crediario crediario = new Crediario();
											CrediarioDAO crediarioDAO = new CrediarioDAO();
													
											crediario.setCliente(cliente);
											crediario.setVenda(venda);
											
										
											
											venda.setValorTotal(valorTotalNovaVenda);
											venda.setHorario(dataAtual.getTime());
											venda.setCliente(cliente);
											vendaDAO.salvar(venda);
											
											tipoDeVenda.setTipo("Troca + Crediario");
											tipoDeVenda.setVenda(venda);
											tipoDeVendaDAO.merge(tipoDeVenda);
											
											
											for(int i = 0; i < itensNovaVenda.size(); i++){
												
												itemNovaVenda = itensNovaVenda.get(i);
												itemNovaVenda.setVenda(venda);
												itemVendaDAO.salvar(itemNovaVenda);
												
												//atualizar estoque
												produto = produtoDAO.buscar(itemNovaVenda.getProduto().getCodigo());
												short aux = (short) (produto.getQuantidade()- itemNovaVenda.getQuantidade());
												produto.setQuantidade(aux);
												produtoDAO.merge(produto);
												
												produto = new Produto();
												aux = 0;
																	
											}
											
											calcularParcelas();
											parcela = valorDaDiferenca.divide(new BigDecimal(numeroDeParcelas));
											for(int i = 0; i<numeroDeParcelas; i++){
												// 30 dias ao vencimento
												dataAtual.add(Calendar.DAY_OF_MONTH, 30);
												crediario.setVencimento(dataAtual.getTime());
												crediario.setParcela(i+1 + " de " + numeroDeParcelas);
												
												crediario.setValor(parcela);
												crediarioDAO.salvar(crediario);
											}
								
											tipoDeVenda = new TipoDeVenda();
											valorDaDiferenca = new BigDecimal(0);
											itemNovaVenda = new ItemVenda();
											funcionario = new Funcionario();
											cliente = new Cliente();
											itensNovaVenda.clear();	
											itensDaVendaDaTroca.clear();
											itensSelecionadosParaTroca.clear();
											itensNovaVenda.clear();
											
											Messages.addFlashGlobalInfo("TROCA REALIZADA COM SUCESSO!");
								}else{
									Messages.addGlobalError("ERRO, Deve ser selecionado um cliente!");
									
								}	

							}else if(valorDaDiferenca.doubleValue()  == 0){
								
								tipoDeVenda = new TipoDeVenda();
								valorDaDiferenca = new BigDecimal(0);
								itemNovaVenda = new ItemVenda();
								funcionario = new Funcionario();
								cliente = new Cliente();
								itensNovaVenda.clear();	
								itensDaVendaDaTroca.clear();
								itensSelecionadosParaTroca.clear();
								itensNovaVenda.clear();
								Messages.addFlashGlobalInfo("TROCA REALIZADA COM SUCESSO!");
							}
				
				}catch (Exception e) {
						Messages.addGlobalError("Ouve um erro dentro do processo de troca, favor checar a troca");
						e.printStackTrace();
				}
				
		}else{
			Messages.addGlobalError("Ouve um erro, os produtos selecionados tem um valor inferior a troca, ou o numero de parcelas está incorreto");
		}
	}else{
		Messages.addGlobalError("Ouve um erro, nenhum item de troca selecionado!");
	}
	
}

	
}
