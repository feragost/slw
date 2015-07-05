package wrapper.tokenizador;

public class Substring {
	
	private boolean tokeniza;
	private String valor;
	
	public Substring(String s, boolean tokeniza){
		this.valor = s;
		this.tokeniza = tokeniza;
	}

	public boolean isTokeniza() {
		return tokeniza;
	}

	public String getValor() {
		return valor;
	}
	
	

}
