package wrapper;

import java.util.HashSet;

import wrapper.comum.TipoToken;

public class WrapperConfig {
	
	public static final int numeroMinimoDeRegistrosPorLista = 8;
	public static final int numeroMaximoDeCaracteresPorRegistro = 160;
	public static final int numeroDeDerivacoes = 4;
	
	public static final HashSet<Integer> tiposTokenIdent = getTiposTokenIdent();
	
	
	public static HashSet<Integer> getTiposTokenIdent(){
		
		HashSet<Integer> tiposTokenIdent = new HashSet<Integer>();
		tiposTokenIdent.add(TipoToken.EMAIL.ordinal());
		tiposTokenIdent.add(TipoToken.DATA.ordinal());
		
		return tiposTokenIdent;
		
	}

}
