package wrapper.tokenizador;



public class Token implements Comparable<Token>{
	
	private String valor;
	private int id;
	private int tipo;
	private boolean validoComoSeparador;
	
	protected Token(String valor, int id){
		this.id = id;
		this.valor = valor;
		this.validoComoSeparador = false;
		this.tipo = -1;
	}
	
	public int getId(){
		return this.id;
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

	@Override
	public int compareTo(Token t2) {
		if(id > t2.id)
			return 1;
		else if(id < t2.id)
			return -1;
		
		return 0;
	}
	
	

}
