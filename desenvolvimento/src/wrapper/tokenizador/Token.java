package wrapper.tokenizador;

public class Token {
	
	private String valor;
	private int tipo;
	private boolean validoComoSeparador;
	
	protected Token(String valor){
		this.valor = valor;
		this.validoComoSeparador = false;
	}
	
		
	public boolean isValidoComoSeparador() {
		return validoComoSeparador;
	}
	
	public void setTipo(int tipo){
		this.tipo = tipo;
	}
	
	public int getTipo(){
		return this.tipo;
	}

	public void setValidoComoSeparador(boolean validoComoSeparador) {
		this.validoComoSeparador = validoComoSeparador;
	}

	public String getValor(){
		return this.valor;
	}
	
	

}
