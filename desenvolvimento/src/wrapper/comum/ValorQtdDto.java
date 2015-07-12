package wrapper.comum;

public class ValorQtdDto {
	
	private String valor;
	private int qtd;
	
	public ValorQtdDto(String valor, int qtd){
		this.valor = valor;
		this.qtd = qtd;
	}

	public String getValor() {
		return valor;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	
	

}
