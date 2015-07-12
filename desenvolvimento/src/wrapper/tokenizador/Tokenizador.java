package wrapper.tokenizador;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wrapper.comum.Token;

public class Tokenizador{
	
	private ArrayList<Padrao> padroes;
	private TokensCtrl tokensCtrl;
	private boolean tokenizarRestos;
	private int tipoDefault;
	
	public Tokenizador(){
		this.padroes = new ArrayList<Padrao>();
		this.tokensCtrl = new TokensCtrl();
	}
	
	
	
	public void setTokenizarRestos(boolean b){
		this.tokenizarRestos = b;
	}
	
	public void setTipoDefault(int tipo){
		this.tipoDefault = tipo;
	}
		
	public void addPadroes(Padrao padrao){
		padroes.add(padrao);
	}

	
	public Token[] tokenizar(String texto) {
		
		if(padroes.size() == 0){
			throw new RuntimeException("Não existem padrões definidos.");
		}
		
		LinkedList<Token> tokenList = tokenizar(texto, 0);		
		return tokenList.toArray(new Token[tokenList.size()]);
		
		
	}
	
	private LinkedList<Token> tokenizar(String texto, int indexSeparador){
				
		LinkedList<Token> tokenList = new LinkedList<Token>();
		
		texto = texto.trim();
		
		if(texto.length() == 0){
			return new LinkedList<Token>();
		}
		
		if(indexSeparador >= padroes.size()){			
			
			if(tokenizarRestos){
				Token token = tokensCtrl.getToken(texto);
				token.setTipo(tipoDefault);
				tokenList.add(token);
			}
			
		}else{
			
			Padrao padrao = padroes.get(indexSeparador);
			LinkedList<Substring> substrings = getSubstrings(padrao, texto);
			for(Substring subs : substrings){
				
				if(subs.isTokeniza()){
					Token token = tokensCtrl.getToken(subs.getValor());
					token.setTipo(padrao.getTipo());
					tokenList.add(token);					
				}else{
					LinkedList<Token> novaTokenList = tokenizar(subs.getValor(), indexSeparador + 1);
					tokenList.addAll(novaTokenList);
				}
				
			}
			
		}
		
		return tokenList;
	}
	
	
	
	private LinkedList<Substring> getSubstrings(Padrao padrao, String texto){
		
		LinkedList<Substring> substrings = new LinkedList<Substring>();
		
		Pattern pattern = padrao.getPattern();		
		Matcher matcher = pattern.matcher(texto);		
		
		int pos = 0;
		while(matcher.find()){
			
			int posInicioMatch = matcher.start();
			String valorAnterior = texto.substring(pos, posInicioMatch);
			String valorToken = matcher.group();			
			
			Substring subsNoTok = new Substring(valorAnterior, false);
			Substring subsTok = new Substring(valorToken, true);
			
			substrings.addLast(subsNoTok);
			
			if(padrao.isTokeniza())
				substrings.addLast(subsTok);
			
			pos = posInicioMatch + valorToken.length();			
		}
		
		String valorAnterior = texto.substring(pos, texto.length());
		Substring subsNoTok = new Substring(valorAnterior, false);
		substrings.addLast(subsNoTok);
		
		return substrings;
	}
	
	
	
	
	
	
	
	
	
	

}


