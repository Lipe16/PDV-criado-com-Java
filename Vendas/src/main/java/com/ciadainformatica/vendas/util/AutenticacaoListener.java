package com.ciadainformatica.vendas.util;


import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import com.ciadainformatica.vendas.bean.AutenticacaoBean;
import com.ciadainformatica.vendas.domain.Usuario;


@SuppressWarnings("serial")
public class AutenticacaoListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
		String paginaAtual = Faces.getViewId();
	
		boolean ehPaginaDeAutenticacao = paginaAtual.contains("autenticacao.xhtml");
	
		if(!ehPaginaDeAutenticacao){
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");

			if(autenticacaoBean == null){
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}
			
			Usuario usuario = autenticacaoBean.getUsuarioLogado();
			if(usuario == null){
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}
			

			
			if(paginaAtual.contains("usuarios.xhtml") || paginaAtual.contains("funcionarios.xhtml") ){
				if(usuario.getTipo() == 'A' || usuario.getTipo() == 'G'){	
				}else{
					Faces.navigate("/pages/principal.xhtml");
					Messages.addFlashGlobalError("Você não tem privilégios para entrar nesta seção!");
					return;
				}
			}
			
			if(paginaAtual.contains("produtos.xhtml") || paginaAtual.contains("fabricantes.xhtml") ){
				if(usuario.getTipo() == 'A' || usuario.getTipo() == 'G'){	
				}else{
					Faces.navigate("/pages/principal.xhtml");
					Messages.addFlashGlobalError("Você não tem privilégios para entrar nesta seção!");
					return;
				}
			}
			
			if(paginaAtual.contains("cidades.xhtml") || paginaAtual.contains("estados.xhtml") ){
				if(usuario.getTipo() == 'A' || usuario.getTipo() == 'G'){	
				}else{
					Faces.navigate("/pages/principal.xhtml");
					Messages.addFlashGlobalError("Você não tem privilégios para entrar nesta seção!");
					return;
				}
			}
			
			if(paginaAtual.contains("troca.xhtml") || paginaAtual.contains("financeiro.xhtml") ){
				if(usuario.getTipo() == 'A' || usuario.getTipo() == 'G'){	
				}else{
					Faces.navigate("/pages/principal.xhtml");
					Messages.addFlashGlobalError("Você não tem privilégios para entrar nesta seção!");
					return;
				}
			}
			
			if(paginaAtual.contains("cancelarVenda.xhtml")){
				if(usuario.getTipo() == 'A' || usuario.getTipo() == 'G'){	
				}else{
					Faces.navigate("/pages/principal.xhtml");
					Messages.addFlashGlobalError("Você não tem privilégios para entrar nesta seção!");
					return;
				}
			}
			
		
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}