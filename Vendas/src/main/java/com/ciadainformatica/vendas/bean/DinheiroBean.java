package com.ciadainformatica.vendas.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import com.ciadainformatica.vendas.dao.DinheiroDAO;
import com.ciadainformatica.vendas.dao.PessoaDAO;
import com.ciadainformatica.vendas.domain.Dinheiro;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class DinheiroBean implements Serializable{
	Dinheiro dinheiro;
	BigDecimal valorInformado;

	
	
	public BigDecimal getValorInformado() {
		return valorInformado;
	}

	public void setValorInformado(BigDecimal valorInformado) {
		this.valorInformado = valorInformado;
	}

	public Dinheiro getDinheiro() {
		return dinheiro;
	}

	public void setDinheiro(Dinheiro dinheiro) {
		this.dinheiro = dinheiro;
	}
	
	
	//preenche uma lista com registro de estados
		@PostConstruct // essa anotation diz que o metodo tem que disparar no momento em que a tela é criada 
		public void listar(){
			valorInformado = new BigDecimal("0");
			try{
				dinheiro = null;
				DinheiroDAO dinheiroDAO = new DinheiroDAO();
				dinheiro = dinheiroDAO.buscar(1l);
			} catch (RuntimeException erro) {
				Messages.addGlobalError("Ocorreu um erro ao tentar verificar o dinheiro");
				erro.printStackTrace();
			}
		}
		
		public void sacarDinheiro(){
			
			dinheiro = null;
			try{
					DinheiroDAO dinheiroDAO = new DinheiroDAO();
					dinheiro = dinheiroDAO.buscar(1l);
					
					if(valorInformado.doubleValue() < dinheiro.getDinheiro().doubleValue()){
						
						dinheiro.setDinheiro(dinheiro.getDinheiro().subtract(valorInformado)); 
						dinheiroDAO.editar(dinheiro);
					}else{
						Messages.addGlobalError("Ocorreu um erro ao tentar verificar o dinheiro");
						Messages.addGlobalError("Ocorreu um erro ao tentar sacar o dinheiro no sistema");
						Messages.addGlobalError("Tente refazer a transação, 'valor a ser sacado não pode ser superior ao informado pelo sistema!'");
					}
			
			} catch (RuntimeException erro) {
				Messages.addGlobalError("Ocorreu um erro ao tentar verificar o dinheiro");
				erro.printStackTrace();
			}
		}
			
		

}
