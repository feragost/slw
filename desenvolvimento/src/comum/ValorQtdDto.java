package comum;

import java.util.LinkedList;
import java.util.List;

import wrapper.comum.Token;

public class ValorQtdDto {
	
	private String valor;
	private int qtd;
	private List<Token> ident;
	
	public ValorQtdDto(String valor, int qtd){
		this.valor = valor;
		this.qtd = qtd;
		this.ident = new LinkedList<Token>();
	}

	public String getValor() {
		return valor;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	
	public void addIdent(List<Token> tokens){
		this.ident = tokens;
	}
	
	public Token[] getIdent(){
		return this.ident.toArray(new Token[ident.size()]);
	}
	
	

}
