package wrapper;

import java.util.HashSet;

import crawler.Settings;
import wrapper.comum.TipoToken;

public class WrapperConfig {
	
	private Settings settings;
	
	private int numeroMinimoDeRegistrosPorLista;
	private int numeroMaximoDeCaracteresPorRegistro;
	private int numeroDeDerivacoes;
	
	private static final int numeroMinimoDeRegistrosPorListaDefault = 8;
	private static final int numeroMaximoDeCaracteresPorRegistroDefault = 160;
	private static final int numeroDeDerivacoesDefault = 4;
	
	public static final HashSet<Integer> tiposTokenIdent = getTiposTokenIdent();
	
	
	private static WrapperConfig instance;
	
	private WrapperConfig(Settings settings){
		this.settings = settings;
		this.numeroMinimoDeRegistrosPorLista = settings.getNumMinRegistros();
		this.numeroMaximoDeCaracteresPorRegistro = settings.getNumMaxCaracteres();
		this.numeroDeDerivacoes = settings.getNumDeDerivacoes();
	}
	
	public static WrapperConfig getInstance(){			
		return instance;
	}
	
	public static void defineSettings(Settings settings){
		instance = new WrapperConfig(settings);
	}
	
	public int getNumeroMinimoDeRegistrosPorLista(){
		return this.numeroMinimoDeRegistrosPorLista;
	}
	
	public int getNumeroMaximoDeCaracteresPorRegistro(){
		return this.numeroMaximoDeCaracteresPorRegistro;
	}
	
	public int getNumeroDeDerivacoes(){
		return this.numeroDeDerivacoes;
	}
	
	public Settings getSettings(){
		return this.settings;
	}
	
	public static HashSet<Integer> getTiposTokenIdent(){
		
		HashSet<Integer> tiposTokenIdent = new HashSet<Integer>();
		tiposTokenIdent.add(TipoToken.EMAIL.ordinal());
		tiposTokenIdent.add(TipoToken.DATA.ordinal());
		
		return tiposTokenIdent;
		
	}

}
