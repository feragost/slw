package wrapper.tokenizador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PadraoDeSeparacao {
	
	private String regex;
	private boolean tokeniza;
	private boolean separador;
	private Pattern pattern;
	private int tipo;
	
	public PadraoDeSeparacao(String regex, boolean tokeniza, boolean separador, int tipo){
		this.regex = regex;
		this.tokeniza = tokeniza;
		this.separador = separador;
		this.pattern = Pattern.compile(regex);
		this.tipo = tipo;
	}
	
	public int getTipo(){
		return this.tipo;
	}

	public String getRegex() {
		return regex;
	}

	public boolean isTokeniza() {
		return tokeniza;
	}
	
	public boolean isSeparador(){
		return separador;
	}
	
	public Pattern getPattern(){
		return pattern;
	}
	
	

}

