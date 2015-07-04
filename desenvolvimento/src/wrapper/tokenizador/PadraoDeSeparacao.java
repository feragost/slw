package wrapper.tokenizador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PadraoDeSeparacao {
	
	private String regex;
	private boolean tokeniza;
	private boolean separador;
	private Pattern pattern;
	
	public PadraoDeSeparacao(String regex, boolean tokeniza, boolean separador){
		this.regex = regex;
		this.tokeniza = tokeniza;
		this.separador = separador;
		this.pattern = Pattern.compile(regex);
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
	
	public Matcher parser(String texto){
		return pattern.matcher(texto);
	}
	
	

}

