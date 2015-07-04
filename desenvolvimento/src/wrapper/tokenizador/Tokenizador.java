package wrapper.tokenizador;

import java.util.LinkedList;
import java.util.regex.Matcher;

public class Tokenizador{
	
	private LinkedList<PadraoDeSeparacao> padroes;
	private TokensCtrl tokensCtrl;
	
	public Tokenizador(){
		this.padroes = new LinkedList<PadraoDeSeparacao>();
		this.tokensCtrl = new TokensCtrl();
	}
		
	public void addPadroes(PadraoDeSeparacao padrao){
		padroes.addLast(padrao);
	}

	
	public Token[] tokenizar(String texto) {
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	

}


