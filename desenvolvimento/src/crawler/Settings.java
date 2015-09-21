package crawler;

public enum Settings {
	
	SETT1(1,3,160,2),
	SETT2(2,6,80,2);
	
	private final int id;  
    private final int numMinRegistros; 
    private final int numMaxCaracteres; 
    private final int numeroDeDerivacoes;
	
    Settings(int id, int numMinRegistros, int numMaxCaracteres, int numDerivacoes){
		this.id = id;
		this.numMinRegistros = numMinRegistros;
		this.numMaxCaracteres = numMaxCaracteres;
		this.numeroDeDerivacoes = numDerivacoes;
	}
	
	public int getId() { return this.id; }
	public int getNumMinRegistros() { return this.numMinRegistros; }
	public int getNumMaxCaracteres() { return this.numMaxCaracteres; }
	public int getNumDeDerivacoes() { return this.numeroDeDerivacoes; }

}
