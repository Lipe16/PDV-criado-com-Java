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

import com.ciadainformatica.vendas.dao.ClienteDAO;
import com.ciadainformatica.vendas.dao.CrediarioDAO;
import com.ciadainformatica.vendas.dao.DinheiroDAO;
import com.ciadainformatica.vendas.dao.RecebimentoDAO;
import com.ciadainformatica.vendas.dao.VendaDAO;
import com.ciadainformatica.vendas.domain.Cidade;
import com.ciadainformatica.vendas.domain.Cliente;
import com.ciadainformatica.vendas.domain.Crediario;
import com.ciadainformatica.vendas.domain.Recebimento;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CrediarioBean implements Serializable{
	private long codigo;
	private List<Crediario> parcelas;
	private List<Crediario> parcelasSelecionadas;
	private BigDecimal valorTotal, troco, valorEmDinheiro;
	private Cliente cliente;
	private Date data  = new Date(System.currentTimeMillis());
	private List<Crediario> aux01;
	
	
	public BigDecimal getValorEmDinheiro() {
		return valorEmDinheiro;
	}
	public void setValorEmDinheiro(BigDecimal valorEmDinheiro) {
		this.valorEmDinheiro = valorEmDinheiro;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
	public List<Crediario> getParcelasSelecionadas() {
		return parcelasSelecionadas;
	}
	public void setParcelasSelecionadas(List<Crediario> parcelasSelecionadas) {
		this.parcelasSelecionadas = parcelasSelecionadas;
	}
	public List<Crediario> getParcelas() {
		return parcelas;
	}
	public void setParcelas(List<Crediario> parcelas) {
		this.parcelas = parcelas;
	}	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	
	
	//preenche uma lista com registro de estados
	@PostConstruct // essa anotation diz que o metodo tem que disparar no momento em que a tela é criada 
	public void listar(){
		cliente = null;
		parcelas = null;
		aux01 = null;
		valorTotal = new BigDecimal(0);
		troco = new BigDecimal(0);
		valorEmDinheiro = new BigDecimal(0);
				
	}
	
	
	public void procurarParcelas(){
		try{
			CrediarioDAO crediarioDAO = new CrediarioDAO();
			parcelas = null;
			parcelas = crediarioDAO.pesquisar(codigo);
			
			cliente = null;
			ClienteDAO clienteDAO = new ClienteDAO();
			cliente = clienteDAO.buscar(codigo);
			
		}catch (Exception e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar");
			e.printStackTrace();
		}
		
	}
	
	
	public void mostrarValorTotal(){
		valorTotal = new BigDecimal(0);
		for(int i = 0; i < parcelasSelecionadas.size(); i++){
			valorTotal = valorTotal.add(parcelasSelecionadas.get(i).getValor());
		}
	}
	
	public void mostrarTroco(){
		troco = valorEmDinheiro.subtract(valorTotal);
		
	}
	
	
	public void excluirParcelaD(){
		
		// 25 20 30 24
		// 
	     
		
		Crediario aux02;
		aux01 = parcelasSelecionadas;
		// organizar por data
		for(int i = 0; i < aux01.size(); i++){
			for(int y = 0; y < aux01.size(); y++){
				
				System.out.println("teste");	
				if(aux01.get(i).getCodigo() < aux01.get(y).getCodigo() && y > i){
					
						aux02 = aux01.get(y);
						aux01.remove(y);
						aux01.add(y, aux01.get(i));
						aux01.remove(i);
						aux01.add(i, aux02);
							
						
				}
				
			}
		}
		
		
		parcelasSelecionadas = aux01;
		
		
			for(int i = parcelasSelecionadas.size()-1; i >= 0; i--){
				valorEmDinheiro = valorEmDinheiro.subtract(parcelasSelecionadas.get(i).getValor());
				if(valorEmDinheiro.doubleValue() >= 0){
					
								try{
									RecebimentoDAO recebimentoDAO = new  RecebimentoDAO();
									Recebimento recebimento = new Recebimento();
									
									recebimento.setVenda(parcelasSelecionadas.get(i).getVenda());
									recebimento.setValor(parcelasSelecionadas.get(i).getValor());
									recebimento.setFormaDeRecebimento("Dinheiro");
									recebimento.setParcela(parcelasSelecionadas.get(i).getValorETotal());
									recebimento.setDataDoRecebimento(data);
									
									recebimentoDAO.salvar(recebimento);
									
									CrediarioDAO crediarioDAO = new CrediarioDAO();
									crediarioDAO.excluir(parcelasSelecionadas.get(i));

									DinheiroDAO dinheiroDAO = new DinheiroDAO();
									dinheiroDAO.atualizar(parcelasSelecionadas.get(i).getValor());// atualiza dinheiro do sistema
									
									Messages.addGlobalInfo("Operação realizada com sucesso");
								}catch (Exception erro) {
									// TODO: handle exception
									Messages.addGlobalError("Ocorreu um erro ao tentar excluir a parcela ");
									erro.printStackTrace();
								}
								
				}else if(valorEmDinheiro.doubleValue() < 0 && (valorEmDinheiro.doubleValue() * -1) <  parcelasSelecionadas.get(i).getValor().doubleValue()){
					
								try{
									RecebimentoDAO recebimentoDAO = new  RecebimentoDAO();
									Recebimento recebimento = new Recebimento();
									
									recebimento.setVenda(parcelasSelecionadas.get(i).getVenda());
									
									recebimento.setFormaDeRecebimento("Dinheiro");
									recebimento.setParcela(parcelasSelecionadas.get(i).getValorETotal());
									recebimento.setDataDoRecebimento(data);
									
							
							
									DinheiroDAO dinheiroDAO = new DinheiroDAO();
									dinheiroDAO.atualizar(valorEmDinheiro.add(parcelasSelecionadas.get(i).getValor()));// atualiza dinheiro do sistema
									
									recebimento.setValor(valorEmDinheiro.add(parcelasSelecionadas.get(i).getValor()));
									
									recebimentoDAO.salvar(recebimento);
									
									
									parcelasSelecionadas.get(i).setValor(valorEmDinheiro.multiply(new BigDecimal(-1)) );
									
									
									CrediarioDAO crediarioDAO = new CrediarioDAO();
									crediarioDAO.editar(parcelasSelecionadas.get(i));
									
									Messages.addGlobalInfo("Operação realizada com sucesso");
									Messages.addGlobalInfo("Atenção, a quantinha em dinheiro não foi suficiente para a(s) parcela(s)");
									
								}catch (Exception erro) {
									// TODO: handle exception
									Messages.addGlobalError("Ocorreu um erro ao tentar excluir a parcela ");
									erro.printStackTrace();
								}	
				}else{
					Messages.addGlobalInfo("Atenção, a quantinha em dinheiro não foi suficiente para a(s) parcela(s)");
					
				}
			}
			
			valorTotal = new BigDecimal(0);
			troco = new BigDecimal(0);
			valorEmDinheiro = new BigDecimal(0);
			parcelasSelecionadas = null;
			procurarParcelas();
		
	}
	
	
	public void excluirParcelaCD(){
		
		for(int i = 0; i < parcelasSelecionadas.size(); i++){

				
							try{
								RecebimentoDAO recebimentoDAO = new  RecebimentoDAO();
								Recebimento recebimento = new Recebimento();
								
								recebimento.setVenda(parcelasSelecionadas.get(i).getVenda());
								recebimento.setValor(parcelasSelecionadas.get(i).getValor());
								recebimento.setFormaDeRecebimento("Cartão de Débito");
								recebimento.setParcela(parcelasSelecionadas.get(i).getValorETotal());
								recebimento.setDataDoRecebimento(data);
								
								recebimentoDAO.salvar(recebimento);
								
								
								CrediarioDAO crediarioDAO = new CrediarioDAO();
								crediarioDAO.excluir(parcelasSelecionadas.get(i));

								//DinheiroDAO dinheiroDAO = new DinheiroDAO();
								//dinheiroDAO.atualizar(parcelasSelecionadas.get(i).getValor());// atualiza dinheiro do sistema

								
								Messages.addGlobalInfo("Operação realizada com sucesso");
							}catch (Exception erro) {
								// TODO: handle exception
								Messages.addGlobalError("Ocorreu um erro ao tentar excluir a parcela ");
								erro.printStackTrace();
							}

		}
		
		valorTotal = new BigDecimal(0);
		troco = new BigDecimal(0);
		valorEmDinheiro = new BigDecimal(0);
		parcelasSelecionadas = null;
		procurarParcelas();
	
}
	
	
	public void excluirParcelaCC(){
		
		for(int i = 0; i < parcelasSelecionadas.size(); i++){

				
							try{
								RecebimentoDAO recebimentoDAO = new  RecebimentoDAO();
								Recebimento recebimento = new Recebimento();
								
								recebimento.setVenda(parcelasSelecionadas.get(i).getVenda());
								recebimento.setValor(parcelasSelecionadas.get(i).getValor());
								recebimento.setFormaDeRecebimento("Cartão de Crédito");
								recebimento.setParcela(parcelasSelecionadas.get(i).getValorETotal());
								recebimento.setDataDoRecebimento(data);
								
								recebimentoDAO.salvar(recebimento);
								
								
								CrediarioDAO crediarioDAO = new CrediarioDAO();
								crediarioDAO.excluir(parcelasSelecionadas.get(i));

								//DinheiroDAO dinheiroDAO = new DinheiroDAO();
								//dinheiroDAO.atualizar(parcelasSelecionadas.get(i).getValor());// atualiza dinheiro do sistema

								
								Messages.addGlobalInfo("Operação realizada com sucesso");
							}catch (Exception erro) {
								// TODO: handle exception
								Messages.addGlobalError("Ocorreu um erro ao tentar excluir a parcela ");
								erro.printStackTrace();
							}

		}
		
		valorTotal = new BigDecimal(0);
		troco = new BigDecimal(0);
		valorEmDinheiro = new BigDecimal(0);
		parcelasSelecionadas = null;
		procurarParcelas();
	
}

	
	
}
