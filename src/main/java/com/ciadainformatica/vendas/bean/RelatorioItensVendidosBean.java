package com.ciadainformatica.vendas.bean;

import java.io.Serializable;
import java.util.ArrayList;
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

import com.ciadainformatica.vendas.dao.ItemVendaDAO;
import com.ciadainformatica.vendas.domain.GraficoItemVenda;
import com.ciadainformatica.vendas.domain.ItemVenda;





@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class RelatorioItensVendidosBean implements Serializable{

	private List<ItemVenda> itens;
	private Date dataInicio = new Date(System.currentTimeMillis());
	private Date dataFim  = new Date(System.currentTimeMillis());
	


	

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




	public List<ItemVenda> getItens() {
		return itens;
	}


	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}


	//preenche uma lista com registro de estados
	@PostConstruct // essa anotation diz que o metodo tem que disparar no momento em que a tela é criada 
	public void listar(){
	
		try{
			ItemVendaDAO itemVendaDAO =  new ItemVendaDAO();
			Calendar dataAtual = Calendar.getInstance();
			dataAtual.add(Calendar.DAY_OF_MONTH, -1);
			itens = itemVendaDAO.listarPorData(dataAtual.getTime(), dataFim);
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os estados");
			erro.printStackTrace();
		}
		
	}
	
	
	public void listarPorData(){

		try{
			ItemVendaDAO itemVendaDAO =  new ItemVendaDAO();
			itens = itemVendaDAO.listarPorData(dataInicio, dataFim);
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os estados");
			erro.printStackTrace();
		}
		
	}	
	
	
}