package crawler;

public enum Settings {
	
	SETT1(1, 3, 160, 2, 0.5f, 0.8f),
	SETT2(2, 3, 160, 2, 0.5f, 0.4f),
	
	SETT3(3, 3, 80, 2, 0.5f, 0.8f),
	SETT4(4, 3, 80, 2, 0.5f, 0.4f),
	
	SETT5(5, 6, 160, 2, 0.8f, 0.8f),
	SETT6(6, 6, 160, 2, 0.8f, 0.4f),
	
	SETT7(7, 6, 80, 2, 0.8f, 0.8f),
	SETT8(8, 6, 80, 2, 0.8f, 0.4f);
	
	private final int id;  
    private final int numMinRegistros; 
    private final int numMaxCaracteres; 
    private final int numeroDeDerivacoes;
    private final float percentIdentidadePrevalecente;
    private final float percentRepeticaoEmColuna;
	
    Settings(int id, int numMinRegistros, int numMaxCaracteres, int numDerivacoes, float percentIdentPrevalecente, float percentRepeticaoEmColuna){
		this.id = id;
		this.numMinRegistros = numMinRegistros;
		this.numMaxCaracteres = numMaxCaracteres;
		this.numeroDeDerivacoes = numDerivacoes;
		this.percentIdentidadePrevalecente = percentIdentPrevalecente;
		this.percentRepeticaoEmColuna = percentRepeticaoEmColuna;
	}
	
	public int getId() { return this.id; }
	public int getNumMinRegistros() { return this.numMinRegistros; }
	public int getNumMaxCaracteres() { return this.numMaxCaracteres; }
	public int getNumDeDerivacoes() { return this.numeroDeDerivacoes; }
	public float getPercentIdentidadePrevalecente() { return this.percentIdentidadePrevalecente; }
	public float getPercentualMaximoParaRepeticaoEmColuna() { return this.percentRepeticaoEmColuna; }
	
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
