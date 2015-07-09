package wrapper.agrup;

import java.util.LinkedList;
import java.util.List;

import wrapper.tokenizador.Token;

public class AgruparNodos {
	
	private LinkedList<Grupo> grupos;
	
	private Grupo grupoAtual;
	
	public AgruparNodos(){		
		grupos = new LinkedList<Grupo>();
		grupoAtual = null;	
	}
	
	public List<Grupo> getGrupos(){
		return grupos;
	}
	
	public void add(Nodo nodo){
				
		List<Token> tokensEmComum = nodo.getTokensEmComum();
		List<Integer> tiposDeTokenEmComum = nodo.getTiposDeTokenEmComum();
		
		
		int qtdTokensEmComum = tokensEmComum.size();
		int qtdTipoDeTokensEmComum = tiposDeTokenEmComum.size();
		
		if(qtdTokensEmComum == 0 && qtdTipoDeTokensEmComum == 0){
			
			grupoAtual = null;
			
		}else{
			
			Identidade identidade = getRegistroIdentidadeDoNodo(nodo);
			
			if(grupoAtual == null){
								
				grupoAtual = new Grupo(nodo.getIndexInicial(), nodo.getIndexFinal());
				grupos.add(grupoAtual);
				grupoAtual.setIdentidade(identidade);
				
			}else{
				
				grupoAtual.setIdentidade(identidade);
				grupoAtual.indexFinal = nodo.getIndexFinal();
				
			}			
			
		}
		
	}
	

	
	
	private Identidade getRegistroIdentidadeDoNodo(Nodo nodo){
		
		List<Token> tokensEmComum = nodo.getTokensEmComum();
		List<Integer> tiposDeTokenEmComum = nodo.getTiposDeTokenEmComum();
		
		Identidade identidade = new Identidade();
		
		for(Token token : tokensEmComum){
			identidade.addToken(token);
		}
		
		for(Integer tipo : tiposDeTokenEmComum){
			identidade.addTipoDeToken(tipo);
		}
		
		return identidade;
		
	}

	

}
