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

import com.ciadainformatica.vendas.dao.CrediarioDAO;
import com.ciadainformatica.vendas.dao.VendaDAO;
import com.ciadainformatica.vendas.domain.Crediario;
import com.ciadainformatica.vendas.domain.Venda;





@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class RelatorioCrediarioBean implements Serializable{

	private List<Crediario> crediarios;
	private Date dataInicio = new Date(System.currentTimeMillis());
	private Date dataFim  = new Date(System.currentTimeMillis());


	

	public List<Crediario> getCrediarios() {
		return crediarios;
	}
	public void setCrediarios(List<Crediario> crediarios) {
		this.crediarios = crediarios;
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


	
	
	
	@PostConstruct 
	public void listar(){
		//crediarios = null ;
		try{
			CrediarioDAO crediarioDAO = new CrediarioDAO();
			crediarios = crediarioDAO.listar();
				
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar");
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