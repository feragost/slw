package wrapper;

import java.util.HashSet;

import wrapper.parser.TipoToken;

public class WrapperConfig {
	
	public static final int numeroMinimoDeRegistrosPorLista = 4;
	public static final int numeroMaximoDeCaracteresPorRegistro = 160;
	public static final int numeroDeDerivacoes = 3;
	
	public static final HashSet<Integer> tiposTokenIdent = getTiposTokenIdent();
	
	
	public static HashSet<Integer> getTiposTokenIdent(){
		
		HashSet<Integer> tiposTokenIdent = new HashSet<Integer>();
		tiposTokenIdent.add(TipoToken.EMAIL.ordinal());
		tiposTokenIdent.add(TipoToken.DATA.ordinal());
		
		return tiposTokenIdent;
		
	}

}
