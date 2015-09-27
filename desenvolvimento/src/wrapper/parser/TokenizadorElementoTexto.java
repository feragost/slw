package wrapper.parser;

import wrapper.comum.TipoToken;
import wrapper.comum.Token;
import wrapper.tokenizador.Padrao;
import wrapper.tokenizador.Tokenizador;

public class TokenizadorElementoTexto extends Tokenizador {
	
	public TokenizadorElementoTexto(){
		String regexEmail = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";
		String regexHifenInterno = "(\\w)+(-(\\w)+)+";
		String regexNumero = "(\\d)+([.|,](\\d)+)*";
		String regexEspacoBranco = "\\s";
		String regexCaracteresEspeciais = "(?i)[^0-9a-z·ÈÌÛ˙‡ËÏÚ˘‚ÍÓÙ˚„ıÁ]";
		
		addPadroes(new Padrao(regexEmail, true, false, TipoToken.EMAIL.ordinal()));
		addPadroes(new Padrao(regexHifenInterno, true, false, TipoToken.HIFEN_INTERNO.ordinal()));
		addPadroes(new Padrao(regexNumero, true, false, TipoToken.NUMERO.ordinal()));
		addPadroes(new Padrao(regexEspacoBranco, false, false, TipoToken.ESPACO_BRANCO.ordinal()));
		addPadroes(new Padrao(regexCaracteresEspeciais, true, true, TipoToken.CARACTERE_ESPECIAL.ordinal()));
				
		setTokenizarRestos(true);
		setTipoDefault(TipoToken.WORD.ordinal());
		
	}
	
	public static void main(String[] args) {
		
		TokenizadorElementoTexto tokenizador = new TokenizadorElementoTexto();
		Token[] tokens = tokenizador.tokenizar("fernando@gmail.com - MetalÁlica, (The Beatles, Iron Maidem)");
		
		for(Token token : tokens){
			TipoToken[] tipoTokenValues = TipoToken.values();
			
			System.out.println(tipoTokenValues[token.getTipo()] + " : " + token.getValor());
		}
		
	}

}
