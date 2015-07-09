package wrapper.agrup;

import java.util.Arrays;
import java.util.HashSet;

import wrapper.tokenizador.Token;

public class Identidade {
	
	private HashSet<Token> tokensEmComum;
	private HashSet<Integer> tiposDeTokenEmComum;
	
	
	
	public Token[] getTokensEmComum() {
		return tokensEmComum.toArray(new Token[tokensEmComum.size()]);
	}

	public Integer[] getTiposDeTokenEmComum() {
		return tiposDeTokenEmComum.toArray(new Integer[tiposDeTokenEmComum.size()]);
	}

	public Identidade(){
		tokensEmComum = new HashSet<Token>();
		tiposDeTokenEmComum = new HashSet<Integer>();
	}
	
	public void addToken(Token token){
		tokensEmComum.add(token);
	}
	
	public void addTipoDeToken(Integer tipo){
		tiposDeTokenEmComum.add(tipo);
	}
	
	public String getKey(){
		
		Token[] arrTokens = tokensEmComum.toArray(new Token[tokensEmComum.size()]);
		Arrays.sort(arrTokens);
		
		Integer[] arrTipos = tiposDeTokenEmComum.toArray(new Integer[tiposDeTokenEmComum.size()]);
		Arrays.sort(arrTipos);
		
		String key = "TK:";		
		for(Token token : arrTokens)
			key += token.getId() + ".";
		
		key += "TP:";
		for(Integer tipo : tiposDeTokenEmComum)
			key += tipo + ".";
		
		return key;
	}

}
