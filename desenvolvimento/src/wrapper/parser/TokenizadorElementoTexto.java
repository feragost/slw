package wrapper.parser;

import wrapper.tokenizador.PadraoDeSeparacao;
import wrapper.tokenizador.Token;
import wrapper.tokenizador.Tokenizador;

public class TokenizadorElementoTexto extends Tokenizador {
	
	public TokenizadorElementoTexto(){
		String regexEmail = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";
		String regexHifenInterno = "(\\w)+(-(\\w)+)+";
		String regexNumero = "(\\d)+([.|,](\\d)+)*";
		String regexEspacoBranco = "\\s";
		String regexCaracteresEspeciais = "\\W";
		
		addPadroes(new PadraoDeSeparacao(regexEmail, true, false, TipoToken.EMAIL.ordinal()));
		addPadroes(new PadraoDeSeparacao(regexHifenInterno, true, false, TipoToken.HIFEN_INTERNO.ordinal()));
		addPadroes(new PadraoDeSeparacao(regexNumero, true, false, TipoToken.NUMERO.ordinal()));
		addPadroes(new PadraoDeSeparacao(regexEspacoBranco, false, false, TipoToken.ESPACO_BRANCO.ordinal()));
		addPadroes(new PadraoDeSeparacao(regexCaracteresEspeciais, true, true, TipoToken.CARACTERE_ESPECIAL.ordinal()));
				
		setTokenizarRestos(true);
		setTipoDefault(TipoToken.WORD.ordinal());
		
	}
	
	public static void main(String[] args) {
		
		TokenizadorElementoTexto tokenizador = new TokenizadorElementoTexto();
		Token[] tokens = tokenizador.tokenizar("fernando@gmail.com - Metallica, The Beatles, Iron Maidem");
		
		for(Token token : tokens){
			TipoToken[] tipoTokenValues = TipoToken.values();
			
			System.out.println(tipoTokenValues[token.getTipo()] + " : " + token.getValor());
		}
		
	}

}
