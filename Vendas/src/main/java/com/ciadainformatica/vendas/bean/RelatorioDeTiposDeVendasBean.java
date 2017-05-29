package com.ciadainformatica.vendas.bean;



import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import org.omnifaces.util.Messages;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;

import com.ciadainformatica.vendas.dao.TipoDeVendaDAO;
import com.ciadainformatica.vendas.dao.VendaDAO;
import com.ciadainformatica.vendas.domain.TipoDeVenda;
import com.ciadainformatica.vendas.domain.Venda;







@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class RelatorioDeTiposDeVendasBean implements Serializable{

	private List<TipoDeVenda> ListaDeVendasPorTipo;
	HorizontalBarChartModel model;
	private String tipo ;
	private Date dataInicio = new Date(System.currentTimeMillis());
	private Date dataFim  = new Date(System.currentTimeMillis());
	private BigDecimal valorTotal;


	

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
	
	
	
	
	
	/**
    public void initBarModel() {
        ChartSeries modelVendas = new ChartSeries();
        
        modelVendas.setLabel("Vendas");
        
        for(int i = 0; i< vendas.size(); i++){
        	modelVendas.set(vendas.get(i).getCodigo(), vendas.get(i).getValorTotal());
        }
 
        model.addSeries(modelVendas);
        
        model.setTitle("Gráfico de vendas");
        model.setLegendPosition("e");
        model.setStacked(true);
        
        Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setLabel("Valor");

         
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Código"); 
        
     
         
    }
	
	*/
	
}