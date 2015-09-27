package comum;

import java.util.LinkedList;
import java.util.List;

import wrapper.comum.Token;

public class RegistroDto {
	
	private String[] atributos;
	private List<Token> tokens;
	
	public RegistroDto(){
		this.tokens = new LinkedList<Token>();
		this.atributos = new String[0];
	}
	
	public void addToken(Token token){
		this.tokens.add(token);
	}
	
	public void addAtributos(String[] atributos){
		this.atributos = atributos;
	}
	
	public Token[] getTokens(){
		return this.tokens.toArray(new Token[tokens.size()]);
	}
	
	public String[] getAtributos(){
		return this.atributos;
	}
	
	

}
