package wrapper.comum;

public class IdentTipoTokenItem implements IdentItem {
	
	private Integer tipo;
	
	public IdentTipoTokenItem(Integer tipo){
		this.tipo = tipo;
	}
	
	public Integer getTipo(){
		return this.tipo;
	}

}
