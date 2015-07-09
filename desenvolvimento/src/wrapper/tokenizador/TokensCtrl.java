package wrapper.tokenizador;

import java.util.Hashtable;

public class TokensCtrl {
	
	private Hashtable<String,Token> hash;
	private int idCtrl;
	
	public TokensCtrl(){
		this.hash = new Hashtable<String, Token>();
		this.idCtrl = 0;
	}
	
	public Token getToken(String valorToken){
		if(!hash.containsKey(valorToken))
			hash.put(valorToken, new Token(valorToken, ++idCtrl));
		return hash.get(valorToken);
	}

}