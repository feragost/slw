package wrapper.parser;

import wrapper.tokenizador.PadraoDeSeparacao;
import wrapper.tokenizador.Tokenizador;

public class TokenizadorElementoTexto extends Tokenizador {
	
	public TokenizadorElementoTexto(){
		String regexEmail = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";
		String regexHifenInterno = "(\\w)+(-(\\w)+)*";
		String regexNumero = "(\\d)+([.|,](\\d)+)*";
		String regexEspacoBranco = "\\s";
		String regexCaracteresEspeciais = "\\W";
		
		addPadroes(new PadraoDeSeparacao(regexEmail, true, false));
		addPadroes(new PadraoDeSeparacao(regexHifenInterno, true, false));
		addPadroes(new PadraoDeSeparacao(regexNumero, true, false));
		addPadroes(new PadraoDeSeparacao(regexEspacoBranco, false, false));
		addPadroes(new PadraoDeSeparacao(regexCaracteresEspeciais, true, true));
	}

}
