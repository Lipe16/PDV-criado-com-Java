package com.ciadainformatica.vendas.bean;



import java.io.Serializable;
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

import com.ciadainformatica.vendas.dao.VendaDAO;
import com.ciadainformatica.vendas.domain.Venda;







@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class RelatorioVendasBean implements Serializable{

	private List<Venda> vendas;
	HorizontalBarChartModel model;
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


	public HorizontalBarChartModel getModel() {
		return model;
	}


	public void setModel(HorizontalBarChartModel model) {
		this.model = model;
	}

	public List<Venda> getVendas() {
		return vendas;
	}


	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}


	//preenche uma lista com registro de estados
	@PostConstruct // essa anotation diz que o metodo tem que disparar no momento em que a tela é criada 
	public void listar(){
		model = new HorizontalBarChartModel();
		try{
			VendaDAO vendaDAO =  new VendaDAO();
			Calendar dataAtual = Calendar.getInstance();
			dataAtual.add(Calendar.DAY_OF_MONTH, -1);
			
			vendas = vendaDAO.listarPorData(dataAtual.getTime(), dataFim);
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as vendas");
			erro.printStackTrace();
		}
		
		
	}
	
	
	
	
	public void listarPorData(){
		model = new HorizontalBarChartModel();
		try{
	
			VendaDAO vendaDAO =  new VendaDAO();
			
			vendas= null;
			vendas = vendaDAO.listarPorData(dataInicio, dataFim);
				
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