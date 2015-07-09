package wrapper.agrup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import wrapper.tokenizador.Token;

public abstract class Nodo {
	
	private HashSet<Token> tokensEmComum;
	private HashSet<Integer> tiposDeTokenEmComum;
	
	private int indexInicial;
	private int indexFinal;
	
	public Nodo(int indexInicial, int indexFinal){
		
		this.indexInicial = indexInicial;
		this.indexFinal = indexFinal;
		
		tokensEmComum = new HashSet<Token>();
		tiposDeTokenEmComum = new HashSet<Integer>();
	}
			
	public int getIndexInicial() {
		return indexInicial;
	}

	public int getIndexFinal() {
		return indexFinal;
	}

	public void addTokenEmComum(Token token){
		tokensEmComum.add(token);
	}
	
	public void addTipoDeTokenEmComum(Integer i){
		tiposDeTokenEmComum.add(i);
	}
	
	public List<Token> getTokensEmComum(){
		return new ArrayList<Token>(tokensEmComum);
	}
	
	public List<Integer> getTiposDeTokenEmComum(){
		return new ArrayList<Integer>(tiposDeTokenEmComum);
	}
	
	public boolean contemToken(Token token){
		return tokensEmComum.contains(token);
	}
	
	public boolean contemTipoDeToken(Integer tipo){
		return tiposDeTokenEmComum.contains(tipo);
	}
	
	

}
