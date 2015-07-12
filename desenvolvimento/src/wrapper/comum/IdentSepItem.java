package wrapper.comum;

public class IdentSepItem implements IdentItem {
	
	private Token token;
	
	public IdentSepItem(Token token){
		this.token = token;
	}
	
	public Token getToken(){
		return this.token;
	}

}
