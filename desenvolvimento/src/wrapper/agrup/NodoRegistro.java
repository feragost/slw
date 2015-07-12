package wrapper.agrup;

import java.util.List;

import wrapper.comum.Registro;
import wrapper.comum.Token;

public class NodoRegistro extends Nodo{
	

	public NodoRegistro(Registro r, int index){
		
		super(index, index);
		
		
		List<Token> tokens = r.getCaracteresEspeciais();
		for(Token token : tokens)
			addTokenEmComum(token);
		
		List<Integer> tiposDeToken = r.getTiposDeTokenIdent();
		for(Integer tipo : tiposDeToken)
			addTipoDeTokenEmComum(tipo);		
		
	}
	
	
		

}
