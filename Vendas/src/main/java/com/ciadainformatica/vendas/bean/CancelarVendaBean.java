package com.ciadainformatica.vendas.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.model.chart.HorizontalBarChartModel;

import com.ciadainformatica.vendas.dao.CrediarioDAO;
import com.ciadainformatica.vendas.dao.DinheiroDAO;
import com.ciadainformatica.vendas.dao.ItemVendaDAO;
import com.ciadainformatica.vendas.dao.ProdutoDAO;
import com.ciadainformatica.vendas.dao.TipoDeVendaDAO;
import com.ciadainformatica.vendas.dao.VendaDAO;
import com.ciadainformatica.vendas.domain.Crediario;
import com.ciadainformatica.vendas.domain.Dinheiro;
import com.ciadainformatica.vendas.domain.ItemVenda;
import com.ciadainformatica.vendas.domain.Produto;
import com.ciadainformatica.vendas.domain.TipoDeVenda;
import com.ciadainformatica.vendas.domain.Venda;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CancelarVendaBean implements Serializable{
	private Venda venda;
	private Produto produto;
	
	private List<TipoDeVenda> ListaDeVendasPorTipo;
	HorizontalBarChartModel model;
	private String tipo ;
	private Date dataInicio = new Date(System.currentTimeMillis());
	private Date dataFim  = new Date(System.currentTimeMillis());
	private BigDecimal valorTotal;


	

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


	public BigDecimal getValorTotal() {
		return valorTotal;
	}


	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public Date getDataInicio() {
		return dataInicio;
	}


	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}


	public Date getDataFim() {
		return dataFim;
	}


	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}


	public HorizontalBarChartModel getModel() {
		return model;
	}


	public void setModel(HorizontalBarChartModel model) {
		this.model = model;
	}




	public List<TipoDeVenda> getListaDeVendasPorTipo() {
		return ListaDeVendasPorTipo;
	}


	public void setListaDeVendasPorTipo(List<TipoDeVenda> listaDeVendasPorTipo) {
		ListaDeVendasPorTipo = listaDeVendasPorTipo;
	}


	public void calculaValorTotal(){
		valorTotal = new BigDecimal("0");
		if(ListaDeVendasPorTipo.size() > 0){
			for(int i=0; i<ListaDeVendasPorTipo.size(); i++){
				valorTotal = valorTotal.add(ListaDeVendasPorTipo.get(i).getVenda().getValorTotal());
			}
		}		
	}
	
	
	//preenche uma lista com registro de estados
	@PostConstruct // essa anotation diz que o metodo tem que disparar no momento em que a tela é criada 
	public void listar(){
		model = new HorizontalBarChartModel();
		try{
			valorTotal = new BigDecimal("0");
			tipo = "Todos";
			TipoDeVendaDAO vendaDAO =  new TipoDeVendaDAO();
			Calendar dataAtual = Calendar.getInstance();
			dataAtual.add(Calendar.DAY_OF_MONTH, -1);
			
			ListaDeVendasPorTipo = vendaDAO.listarPorData(tipo ,dataAtual.getTime(), dataFim);
			calculaValorTotal();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as vendas");
			erro.printStackTrace();
		}
		
		
	}
	
	
	
	
	public void listarPorDataETipo(){
		model = new HorizontalBarChartModel();
		valorTotal = new BigDecimal("0");
		try{
	
			TipoDeVendaDAO vendaDAO =  new TipoDeVendaDAO();
		
			ListaDeVendasPorTipo= null;
			ListaDeVendasPorTipo = vendaDAO.listarPorData(tipo ,dataInicio, dataFim); // lista de vendas obtido
			calculaValorTotal();
			 
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os estados");
			erro.printStackTrace();
		}
	
	}
	
	
	public void excluirVenda(ActionEvent evento){
		try{
			venda = new Venda();
			venda =(Venda) evento.getComponent().getAttributes().get("itemSelecionado");
			
			if(venda.getValorTotal().doubleValue() > 0){

				ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
				ProdutoDAO produtoDAO = new ProdutoDAO();
				
				List<ItemVenda> itensDeVenda = itemVendaDAO.buscarPorCodigoDeVenda(venda.getCodigo());
	
				
				for(int i = 0; i < itensDeVenda.size(); i++){
					produto = new Produto();
			
					
					produto = produtoDAO.buscar(itensDeVenda.get(i).getProduto().getCodigo());
					
					produto.setQuantidade((short) (produto.getQuantidade() + itensDeVenda.get(i).getQuantidade()));				
					produtoDAO.editar(produto);
					
					itemVendaDAO.excluir(itensDeVenda.get(i));
					
				}
				
				DinheiroDAO dinheiroDAO = new DinheiroDAO();
				Dinheiro dinheiro = new Dinheiro();
				
				TipoDeVenda tipoDeVenda = new TipoDeVenda();
				TipoDeVendaDAO tipoDeVendaDAO = new TipoDeVendaDAO();
				
				tipoDeVenda = tipoDeVendaDAO.buscarPorVenda(venda);
				
				System.out.println(tipoDeVenda.getTipo());
				
				if(tipoDeVenda.getTipo().toString().equals("Dinheiro")){
					dinheiro = dinheiroDAO.buscar(1l);
					dinheiro.setDinheiro(dinheiro.getDinheiro().subtract(venda.getValorTotal()));
					dinheiroDAO.editar(dinheiro);
					Messages.addGlobalInfo("Valor em dinheiro foi subtraido do sistema, favor devolver ao cliente sem sacar do sistema!");
				}else{
					Messages.addGlobalInfo("como a Venda não foi inteiramente em dinheiro, deve-se estorna o cartão ou sacar dinheiro do sistema manualmente!");
				}
				
				//excluir parcela do crediario
				if(tipoDeVenda.getTipo().toString().equals("Crediario")){
					CrediarioDAO crediarioDAO = new CrediarioDAO();
					
					List<Crediario> parcelas = crediarioDAO.buscarPorCodigoDeVenda(venda.getCodigo());
					
					for(int i = 0; i < parcelas.size(); i++){

						
						crediarioDAO.excluir(parcelas.get(i));
						
					}
					
					Messages.addGlobalInfo("parcelas referentes a esta venda, foram excluidas!");
				}else{
					Messages.addGlobalInfo("Se esta venda foi no crediário, favor verificar se as parcelas foram apagadas!");
				}
				
				VendaDAO vendaDAO = new VendaDAO();
				
				venda.setValorTotal(new BigDecimal(0));
				
				vendaDAO.editar(venda);
				

				Messages.addGlobalInfo("Itens da venda foram repostos no estoque e a venda foi zerada com sucesso!");
				
				dinheiro = null;
				venda = null;
				itensDeVenda = null;
			}else{
				Messages.addGlobalError("essa venda parece já ter sido apagada!");
			}
			
			
		}catch(Exception erro){
			Messages.addGlobalError("Ouve um erro na hora de apagar");
			erro.printStackTrace();
		}
	}

}
