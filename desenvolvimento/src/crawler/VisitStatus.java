package crawler;

public enum VisitStatus {
	
	NOVA_URL(1,"NOVA URL"),
	LINKS_COLETADOS(2,"LINKS COLETADOS"),
	PROBLEMA(3, "PROBLEMA"),
	PROBLEMA_PARSER(4, "PROBLEMA PARSER"),	
	COLETAR_LISTAS(5, "COLETAR LISTAS"),
	LISTAS_COLETADAS(6, "LISTAS COLETADAS");
	
	private final int id;  
    private final String descricao; 
	
    VisitStatus(int id, String descricao){
		this.id = id;
		this.descricao = descricao;
	}
	
	public int getId() { return this.id; }
	public String getDescricao() { return this.descricao; }

}
