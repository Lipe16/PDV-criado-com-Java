package com.ciadainformatica.vendas.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
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
import com.ciadainformatica.vendas.util.ImprimirDados;






@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class VendaBean implements Serializable{

	private ItemVenda item;
	private List<ItemVenda> itens;
	private Venda venda;
	private Produto produto;
	List<Produto> produtos;
	public String codigo;
	public short quantidade = 1;
	public BigDecimal valorTotal;
	public Funcionario funcionario;
	public Cliente cliente;
	private BigDecimal dinheiro;
	private BigDecimal troco;
	private int numeroDeParcelas;
	private BigDecimal parcela;
	
	
	
	
	
	////// geters e seters************************************		
	public int getNumeroDeParcelas() {
		return numeroDeParcelas;
	}
	public void setNumeroDeParcelas(int numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public BigDecimal getParcela() {
		return parcela;
	}
	public void setParcela(BigDecimal parcela) {
		this.parcela = parcela;
	}

	public BigDecimal getDinheiro() {
		return dinheiro;
	}
	public void setDinheiro(BigDecimal dinheiro) {
		this.dinheiro = dinheiro;
	}
	public BigDecimal getTroco() {
		return troco;
	}
	public void setTroco(BigDecimal troco) {
		this.troco = troco;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public ItemVenda getItem() {
		return item;
	}
	public void setItem(ItemVenda item) {
		this.item = item;
	}
	public List<ItemVenda> getItens() {
		return itens;
	}
	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
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
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public short getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(short quantidade) {
		this.quantidade = quantidade;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}	
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	////// geters e seters****************************
	
	
	
	@PostConstruct // essa anotation diz que o metodo tem que disparar no momento em que a tela é criada 
	public void listar(){
		itens = new ArrayList<ItemVenda>();
		item = new ItemVenda();
		produto = new Produto();
		funcionario = new Funcionario();
		cliente = new Cliente();
		venda = new Venda();
		dinheiro = new BigDecimal("0");
		numeroDeParcelas = 1;
		parcela = new BigDecimal("0");
		
	}
	
	public void adicionarItem(){
		
		if(funcionario != null && funcionario.getCodigo().toString() != ""){
			
			produtos = new ArrayList<Produto>();
			produto = new Produto();
			if(codigo == null || codigo.trim().equals("")){
				Messages.addGlobalError("A pesquisa está vazia");
			}else{
				
				//capturando dados para pesquisa
				try{
				    produto.setCodigo(Long.parseLong(codigo));
				    produto.setCodBarras(codigo);
				}catch(NumberFormatException e){
					//System.out.println(codigo);
					produto.setCodBarras(codigo);
				}
				
				try{
					codigo = "";
					ProdutoDAO produtoDAO = new ProdutoDAO();
					produtos = produtoDAO.pesquisar(produto);
					
				}catch(Exception erro){
					Messages.addGlobalError("Nenhum produto encontrado!");
				}
				
				
				if(produtos.size() == 1){//quando produto é encontrado
					item.setProduto(produtos.get(0));
					item.setFuncionario(funcionario);
					
					
					if(quantidade > 0){
						item.setQuantidade(quantidade);					
						quantidade = 1;
					}else{
						Messages.addGlobalError("não é permitido quantidade menor que 1, logo foi atribuido 1 a quantidade!");
						quantidade = 1;
						item.setQuantidade(quantidade);
					}
					
					for(int i = 0; i<itens.size(); i++){
						if(item.getProduto().getCodigo() == itens.get(i).getProduto().getCodigo()){
							quantidade = (short) (item.getQuantidade() + itens.get(i).getQuantidade());
							item.setQuantidade(quantidade);
							itens.remove(i);
							quantidade = 1;
						}
					}
					item.setValorParcial(item.getProduto().getPreco().multiply(new BigDecimal(item.getQuantidade())));
					//até a linha anterior o item já armazenou os valores que necessita menos venda
					
					itens.add(0,item);	
					item = new ItemVenda();
					valorTotal();
					
					
					
					
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
				item =(ItemVenda) evento.getComponent().getAttributes().get("itemSelecionado");
				
				
				for(int i = 0; i<itens.size(); i++){
					if(item.getProduto().getCodigo() == itens.get(i).getProduto().getCodigo()){
						itens.remove(i);
						Messages.addGlobalError(item.getProduto().getDescricao()+" exlcuido com sucesso!");
						valorTotal();
						item = new ItemVenda();
					}
				}
				

			}catch(Exception erro){
				Messages.addGlobalError("Ouve um erro na hora de apagar");
				erro.printStackTrace();
			}
	}
	
	
	public void editarValorDoItem(RowEditEvent event){
		item = new ItemVenda();
		try{
					item =(ItemVenda) event.getObject();
					
					
					AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
					
					Usuario usuario = autenticacaoBean.getUsuarioLogado();
					
					if(usuario.getTipo() == 'A' || usuario.getTipo() == 'G'){
								System.out.println(item.getQuantidade());
								item.setValorParcial(item.getProduto().getPreco().multiply(new BigDecimal(item.getQuantidade())));
								
								for(int i = 0; i<itens.size(); i++){
									if(item.getProduto().getCodigo() == itens.get(i).getProduto().getCodigo()){
										
										
										itens.set(i, item);
										
									
										Messages.addGlobalInfo(item.getProduto().getDescricao()+" preco alterado com sucesso!");
									
									}
							}
						
						item = new ItemVenda();
						valorTotal();
					}else{
						Messages.addGlobalError("Usuario não autorizado!");

						item = new ItemVenda();
						
						
					}
			}catch(Exception erro){
				Messages.addGlobalError("Ouve um erro na hora de editar valor");
				erro.printStackTrace();
			}

	}
	
	public void cancelarValorDoItem(RowEditEvent event){
		item =(ItemVenda) event.getObject();
		Messages.addGlobalError(item.getProduto().getDescricao()+" com preço cancelado!");
	}
	
	
	
	
	
	
	public void valorTotal(){
		valorTotal = new BigDecimal(0);	
		for(int i = 0; i < itens.size(); i++){
			
			valorTotal = valorTotal.add(itens.get(i).getValorParcial());	
		}

	}

	
	public void calcularTroco(){
		try{
			if(dinheiro.compareTo(new BigDecimal(0)) == 1 && dinheiro != null){
				
					troco = dinheiro.subtract(valorTotal);
	
			}
		}catch(Exception erro){
			Messages.addGlobalError("ocorreu um erro a calcular o troco");
		}
	}
	
	public void calcularParcelas(){
		if(numeroDeParcelas >= 1){
			try{
				parcela = valorTotal.divide(new BigDecimal(numeroDeParcelas), 2, RoundingMode.UP);
				System.out.println(parcela);
			}catch(Exception erro){
				numeroDeParcelas = 1;
				Messages.addGlobalError("ocorreu um erro a calcular as parcelas");
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	public void salvarVendaDinheiro(){
		String dados ="";
		
		dinheiro = new BigDecimal(0);
		troco = new BigDecimal(0);
		
		if(valorTotal.compareTo(new BigDecimal(0)) == 1){
			Calendar dataAtual = Calendar.getInstance();
			
			VendaDAO vendaDAO = new VendaDAO();
			ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produto = new Produto();
			
			TipoDeVenda tipoDeVenda = new TipoDeVenda();
			TipoDeVendaDAO tipoDeVendaDAO = new TipoDeVendaDAO();
			
			DinheiroDAO dinheiroDAO = new DinheiroDAO();
			
			try{
				
				venda.setValorTotal(valorTotal);
				venda.setHorario(dataAtual.getTime());
				venda.setCliente(cliente);
				vendaDAO.salvar(venda);
				
				//pegando dados para impressão
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
				
		
				//pegando dados para impressão
				dados += "\r\nValor Total: "+ valorTotal +"R$\r\n \r\n \r\n \r\n";
				
				dados += "TIPO DE VENDA: Dinheiro\r\n";
				dados += "Venda: "+ venda.getCodigo()+"\r\n";				
				dados += "Horario: "+ sdf.format(venda.getHorario())+"\r\n";
				dados += "Cliente: "+ venda.getCliente()+"\r\n";
				
		
				
				
				tipoDeVenda.setTipo("Dinheiro");
				tipoDeVenda.setVenda(venda);
				tipoDeVendaDAO.merge(tipoDeVenda);
				
				dinheiroDAO.atualizar(valorTotal);// atualiza dinheiro do sistema

				
				for(int i = 0; i < itens.size(); i++){
					
					item = itens.get(i);
					item.setVenda(venda);
					itemVendaDAO.salvar(item);
					
					//pegando dados para imprimir
					dados = "\r\n"+ item.getProduto().getCodigo() +": "+ item.getProduto().getDescricao()+"|QTD: "+item.getQuantidade()+ "|VALOR UN: "+item.getProduto().getPreco()+ "R$|VALOR: "+item.getValorParcial()+ "R$ \r\n"+ dados +"\r\n \r\n";
					
					//atualizar estoque
					produto = produtoDAO.buscar(item.getProduto().getCodigo());
					short aux = (short) (produto.getQuantidade()- item.getQuantidade());
					produto.setQuantidade(aux);
					produtoDAO.merge(produto);
					
					produto = new Produto();
					aux = 0;
										
				}
				

				
				dados = "Nota gerada pelo Sistema de vendas \r\n" +dados;
	
				try{
					ImprimirDados imprimir = new ImprimirDados();
					imprimir.imprime(dados);
				}catch (Exception e) {
					// TODO: handle exception
				}
				
				
				tipoDeVenda = null;
				valorTotal = new BigDecimal(0);
				item = new ItemVenda();
				funcionario = new Funcionario();
				cliente = new Cliente();
				itens.clear();	
				
				
			}catch(Exception erro){
				Messages.addGlobalError("Ouve um erro ao tentar salvar a venda!");			
			}
		}else{
			Messages.addGlobalError("valor total não deve estar zerado");
		}
	}
	
	// salva venda no crediario
	public void salvarVendaCrediario(){
		
		dinheiro = new BigDecimal(0);
		troco = new BigDecimal(0);
		
		if(valorTotal.compareTo(new BigDecimal(0)) == 1 && cliente != null){
			Calendar dataAtual = Calendar.getInstance();
			
			
			VendaDAO vendaDAO = new VendaDAO();
			ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produto = new Produto();
			
			TipoDeVenda tipoDeVenda = new TipoDeVenda();
			TipoDeVendaDAO tipoDeVendaDAO = new TipoDeVendaDAO();
			
			Crediario crediario = new Crediario();
			CrediarioDAO crediarioDAO = new CrediarioDAO();
			
			
			try{
				
			
				venda.setValorTotal(valorTotal);
				venda.setHorario(dataAtual.getTime());
				venda.setCliente(cliente);
				vendaDAO.salvar(venda);
				
				tipoDeVenda.setTipo("Crediario");
				tipoDeVenda.setVenda(venda);
				tipoDeVendaDAO.salvar(tipoDeVenda);
				
				crediario.setCliente(cliente);
				crediario.setVenda(venda);
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
				
				String dados= "";
				//pegando dados para impressão
				dados += "\r\nValor Total: "+ valorTotal +"R$\r\n \r\n \r\n \r\n";
				
				dados += "TIPO DE VENDA: Crediario\r\n";
				dados += "Venda: "+ venda.getCodigo()+"\r\n";				
				dados += "Horario: "+ sdf.format(venda.getHorario())+"\r\n";
				dados += "Cliente: "+ venda.getCliente()+"\r\n";
				
		
					
				for(int i = 0; i < itens.size(); i++){
					
					item = itens.get(i);
					item.setVenda(venda);
					itemVendaDAO.salvar(item);
					
					//pegando dados para imprimir
					dados = "\r\n"+ item.getProduto().getCodigo() +": "+ item.getProduto().getDescricao()+"|QTD: "+item.getQuantidade()+ "|VALOR UN: "+item.getProduto().getPreco()+ "R$|VALOR: "+item.getValorParcial()+ "R$ \r\n"+ dados +"\r\n \r\n";
					
					
					//atualizar estoque
					produto = produtoDAO.buscar(item.getProduto().getCodigo());
					short aux = (short) (produto.getQuantidade()- item.getQuantidade());
					produto.setQuantidade(aux);
					produtoDAO.merge(produto);
					
					produto = new Produto();
					aux = 0;
										
				}
				
				
				
				if(numeroDeParcelas >= 1){
					try{
						parcela = valorTotal.divide(new BigDecimal(numeroDeParcelas), 2, RoundingMode.UP);
						System.out.println(parcela);
					}catch(Exception erro){
						numeroDeParcelas = 1;
						Messages.addGlobalError("ocorreu um erro a calcular as parcelas");
					}
				}else{
					numeroDeParcelas =  1;
						parcela = valorTotal.divide(new BigDecimal(numeroDeParcelas), 2, RoundingMode.UP);
						System.out.println(parcela);

					
				}
				
				SimpleDateFormat sdf02 = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
				for(int i = 0; i<numeroDeParcelas; i++){
					// 30 dias ao vencimento
					dataAtual.add(Calendar.DAY_OF_MONTH, 30);
					crediario.setVencimento(dataAtual.getTime());
					crediario.setParcela(i+1 + " de " + numeroDeParcelas);
					
					
					dados += "\r\n parcela"+ i+1 +"| valor: "+parcela+"| DATA: "+sdf02.format(dataAtual.getTime()).toString()+"\r\n";
					
					crediario.setValor(parcela);
					crediarioDAO.salvar(crediario);
				}
				
				dados = "Nota gerada pelo Sistema de vendas \r\n" +dados;
				
				try{
					ImprimirDados imprimir = new ImprimirDados();
					imprimir.imprime(dados);
				}catch (Exception e) {
					// TODO: handle exception
				}
				
				
				tipoDeVenda = null;
				valorTotal = new BigDecimal(0);
				item = new ItemVenda();
				funcionario = new Funcionario();
				cliente = new Cliente();
				itens.clear();	
				
				
			}catch(Exception erro){
				Messages.addGlobalError("Ouve um erro ao tentar salvar a venda!");	
				erro.printStackTrace();
			}
		}else{
			Messages.addGlobalError("valor total não deve estar zerado e para crediario deve ser informado o cliente");
		}
	}
	
	
	
	
	// salva venda no cartão de Debito
	public void salvarVendaCD(){
		
		dinheiro = new BigDecimal(0);
		troco = new BigDecimal(0);
		
		if(valorTotal.compareTo(new BigDecimal(0)) == 1){
			java.util.Date data = new java.util.Date();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(data.getTime());
			
			VendaDAO vendaDAO = new VendaDAO();
			ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produto = new Produto();
			
			TipoDeVenda tipoDeVenda = new TipoDeVenda();
			TipoDeVendaDAO tipoDeVendaDAO = new TipoDeVendaDAO();
			
			try{
				
			
				venda.setValorTotal(valorTotal);
				venda.setHorario(timestamp);
				venda.setCliente(cliente);
				vendaDAO.salvar(venda);
				
				tipoDeVenda.setTipo("Cartão de Débito");
				tipoDeVenda.setVenda(venda);
				tipoDeVendaDAO.salvar(tipoDeVenda);
				
				tipoDeVenda = null;
				tipoDeVendaDAO = null;
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
				
				String dados= "";
				//pegando dados para impressão
				dados += "\r\nValor Total: "+ valorTotal +"R$\r\n \r\n \r\n \r\n";
				
				dados += "TIPO DE VENDA: Credito\r\n";
				dados += "Venda: "+ venda.getCodigo()+"\r\n";				
				dados += "Horario: "+ sdf.format(venda.getHorario())+"\r\n";
				dados += "Cliente: "+ venda.getCliente()+"\r\n";
			
		
				
				for(int i = 0; i < itens.size(); i++){
					
					item = itens.get(i);
					item.setVenda(venda);
					itemVendaDAO.salvar(item);
					
					//pegando dados para imprimir
					dados = "\r\n"+ item.getProduto().getCodigo() +": "+ item.getProduto().getDescricao()+"|QTD: "+item.getQuantidade()+ "|VALOR UN: "+item.getProduto().getPreco()+ "R$|VALOR: "+item.getValorParcial()+ "R$ \r\n"+ dados +"\r\n \r\n";
					
					
					//atualizar estoque
					produto = produtoDAO.buscar(item.getProduto().getCodigo());
					short aux = (short) (produto.getQuantidade()- item.getQuantidade());
					produto.setQuantidade(aux);
					produtoDAO.merge(produto);
					
					produto = new Produto();
					aux = 0;
										
				}
				
				
				dados = "Nota gerada pelo Sistema de vendas \r\n" +dados;
				
				try{
					ImprimirDados imprimir = new ImprimirDados();
					imprimir.imprime(dados);
				}catch (Exception e) {
					// TODO: handle exception
				}
				
				valorTotal = new BigDecimal(0);
				item = new ItemVenda();
				funcionario = new Funcionario();
				cliente = new Cliente();
				itens.clear();	
				
				
			}catch(Exception erro){
				Messages.addGlobalError("Ouve um erro ao tentar salvar a venda!");			
			}
		}else{
			Messages.addGlobalError("valor total não deve estar zerado");
		}
	}
	
	
	
	
	// salva venda no cartão de credito
	public void salvarVendaCC(){
		
		dinheiro = new BigDecimal(0);
		troco = new BigDecimal(0);
		
		if(valorTotal.compareTo(new BigDecimal(0)) == 1){
			java.util.Date data = new java.util.Date();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(data.getTime());
			
			VendaDAO vendaDAO = new VendaDAO();
			ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produto = new Produto();
			
			TipoDeVenda tipoDeVenda = new TipoDeVenda();
			TipoDeVendaDAO tipoDeVendaDAO = new TipoDeVendaDAO();
			
			try{
				
			
				venda.setValorTotal(valorTotal);
				venda.setHorario(timestamp);
				venda.setCliente(cliente);
				vendaDAO.salvar(venda);
				
				tipoDeVenda.setTipo("Cartão de Crédito");
				tipoDeVenda.setVenda(venda);
				tipoDeVendaDAO.salvar(tipoDeVenda);
				
				tipoDeVenda = null;
				tipoDeVendaDAO = null;
		
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
				
				String dados= "";
				//pegando dados para impressão
				dados += "\r\nValor Total: "+ valorTotal +"R$\r\n \r\n \r\n \r\n";
				
				dados += "TIPO DE VENDA: Debito\r\n";
				dados += "Venda: "+ venda.getCodigo()+"\r\n";				
				dados += "Horario: "+ sdf.format(venda.getHorario())+"\r\n";
				dados += "Cliente: "+ venda.getCliente()+"\r\n";
				
		
				
				
				for(int i = 0; i < itens.size(); i++){
					
					item = itens.get(i);
					item.setVenda(venda);
					itemVendaDAO.salvar(item);
					
					//pegando dados para imprimir
					dados = "\r\n"+ item.getProduto().getCodigo() +": "+ item.getProduto().getDescricao()+"|QTD: "+item.getQuantidade()+ "|VALOR UN: "+item.getProduto().getPreco()+ "R$|VALOR: "+item.getValorParcial()+ "R$ \r\n"+ dados +"\r\n \r\n";
					
					
					//atualizar estoque
					produto = produtoDAO.buscar(item.getProduto().getCodigo());
					short aux = (short) (produto.getQuantidade()- item.getQuantidade());
					produto.setQuantidade(aux);
					produtoDAO.merge(produto);
					
					produto = new Produto();
					aux = 0;
										
				}
				
				dados = "Nota gerada pelo Sistema de vendas \r\n" +dados;
				
				try{
					ImprimirDados imprimir = new ImprimirDados();
					imprimir.imprime(dados);
				}catch (Exception e) {
					// TODO: handle exception
				}
	
				valorTotal = new BigDecimal(0);
				item = new ItemVenda();
				funcionario = new Funcionario();
				cliente = new Cliente();
				itens.clear();	
				
				
			}catch(Exception erro){
				Messages.addGlobalError("Ouve um erro ao tentar salvar a venda!");			
			}
		}else{
			Messages.addGlobalError("valor total não deve estar zerado");
		}
	}


}