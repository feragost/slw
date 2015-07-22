package crawler;

public enum UrlStatus {
	
	RECOLHIDA(1,"RECOLHIDA"),
	VISITADA(2,"VISITADA"),
	PROBLEMA_DOWN(3, "PROBLEMA DOWNLOAD"),
	PROBLEMA_PARSER(4, "PROBLEMA PARSER");
	
	private final int id;  
    private final String descricao; 
	
    UrlStatus(int id, String descricao){
		this.id = id;
		this.descricao = descricao;
	}
	
	public int getId() { return this.id; }
	public String getDescricao() { return this.descricao; }

}
