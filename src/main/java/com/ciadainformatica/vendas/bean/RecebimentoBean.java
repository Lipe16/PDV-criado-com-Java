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
import org.primefaces.model.chart.HorizontalBarChartModel;

import com.ciadainformatica.vendas.dao.RecebimentoDAO;
import com.ciadainformatica.vendas.dao.TipoDeVendaDAO;
import com.ciadainformatica.vendas.domain.Recebimento;
import com.ciadainformatica.vendas.domain.TipoDeVenda;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class RecebimentoBean implements Serializable{
	private List<Recebimento> ListaDeRecebimentos;
	private Date dataInicio = new Date(System.currentTimeMillis());
	private Date dataFim  = new Date(System.currentTimeMillis());
	private BigDecimal valorTotal;
	
	
	
	
	
	
	public List<Recebimento> getListaDeRecebimentos() {
		return ListaDeRecebimentos;
	}
	public void setListaDeRecebimentos(List<Recebimento> listaDeRecebimentos) {
		ListaDeRecebimentos = listaDeRecebimentos;
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
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	
	public void calculaValorTotal(){
		valorTotal = new BigDecimal("0");
		if(ListaDeRecebimentos.size() > 0){
			for(int i=0; i<ListaDeRecebimentos.size(); i++){
				valorTotal = valorTotal.add(ListaDeRecebimentos.get(i).getValor());
			}
		}		
	}
	
	
	//preenche uma lista com registro de estados
	@PostConstruct // essa anotation diz que o metodo tem que disparar no momento em que a tela é criada 
	public void listar(){
		try{
			valorTotal = new BigDecimal("0");
			RecebimentoDAO recebimentoDAO = new RecebimentoDAO();
			Calendar dataAtual = Calendar.getInstance();
			dataAtual.add(Calendar.DAY_OF_MONTH, -1);
			
			ListaDeRecebimentos= recebimentoDAO.listarPorData(dataAtual.getTime(), dataFim);
			calculaValorTotal();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as vendas");
			erro.printStackTrace();
		}
		
		
	}
	
	
	
	public void listarPorData(){

		valorTotal = new BigDecimal("0");
		try{
	
			RecebimentoDAO recebimentoDAO = new RecebimentoDAO();
		
			ListaDeRecebimentos = null;
			ListaDeRecebimentos = recebimentoDAO.listarPorData(dataInicio, dataFim); // lista de vendas obtido
			calculaValorTotal();
			 
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os estados");
			erro.printStackTrace();
		}
	
	}
	
	
}
