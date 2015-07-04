package wrapper.tokenizador;

public class Token {
	
	private String valor;
	private boolean validoComoSeparador;
	
	protected Token(String valor){
		this.valor = valor;
		this.validoComoSeparador = false;
	}
	
		
	public boolean isValidoComoSeparador() {
		return validoComoSeparador;
	}

	public void setValidoComoSeparador(boolean validoComoSeparador) {
		this.validoComoSeparador = validoComoSeparador;
	}


	public String getValor(){
		return this.valor;
	}
	
	public boolean ehValidoComoSeparador(){
		return this.validoComoSeparador;
	}
	
	

}
