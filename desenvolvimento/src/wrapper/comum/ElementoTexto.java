package wrapper.comum;

import wrapper.tokenizador.Tokenizador;

public class ElementoTexto {
	
	private String texto;
	private Path path;
	private Token[] tokens;
	
	public ElementoTexto(String texto, Path path, Tokenizador tokenizador){
		this.texto = texto;
		this.path = path;		
		this.tokens = tokenizador.tokenizar(texto);
	}

	public String getTexto() {
		return texto;
	}

	public Path getPath() {
		return path;
	}
	
	public Token[] getTokens(){
		return tokens;
	}
	
	
	
	

}
