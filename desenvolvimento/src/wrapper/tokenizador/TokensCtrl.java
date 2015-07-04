package wrapper.tokenizador;

import java.util.Hashtable;

public class TokensCtrl {
	
	private Hashtable<String,Token> hash;
	
	public TokensCtrl(){
		hash = new Hashtable<String, Token>();
	}
	
	public Token getToken(String valorToken){
		if(!hash.containsKey(valorToken))
			hash.put(valorToken, new Token(valorToken));
		return hash.get(valorToken);
	}

}