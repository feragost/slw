package crawler;

public enum Settings {
	
	SETT1(1,3,160,2, 0.6f),
	SETT2(2,6,80,2, 0.5f);
	
	private final int id;  
    private final int numMinRegistros; 
    private final int numMaxCaracteres; 
    private final int numeroDeDerivacoes;
    private final float percentIdentidadePrevalecente;
	
    Settings(int id, int numMinRegistros, int numMaxCaracteres, int numDerivacoes, float percentIdentPrevalecente){
		this.id = id;
		this.numMinRegistros = numMinRegistros;
		this.numMaxCaracteres = numMaxCaracteres;
		this.numeroDeDerivacoes = numDerivacoes;
		this.percentIdentidadePrevalecente = percentIdentPrevalecente;
	}
	
	public int getId() { return this.id; }
	public int getNumMinRegistros() { return this.numMinRegistros; }
	public int getNumMaxCaracteres() { return this.numMaxCaracteres; }
	public int getNumDeDerivacoes() { return this.numeroDeDerivacoes; }
	public float getPercentIdentidadePrevalecente() { return this.percentIdentidadePrevalecente; }
	
	public static Settings getSettings(int id){
		Settings[] settingsArray = Settings.values();
		for(Settings settings : settingsArray){
			if(settings.id == id){
				return settings;
			}
		}
		return null;
	}

}
