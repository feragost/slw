package wrapper.agrup;

import java.util.List;

import wrapper.parser.Registro;
import wrapper.tokenizador.Token;

public class NodoDeriv extends Nodo{

	public NodoDeriv(Nodo n1, Nodo n2) {
		super(n1.getIndexInicial(), n2.getIndexFinal());
		
		List<Token> tokens = n1.getTokensEmComum();
		for(Token token : tokens)
			if(n2.contemToken(token))
				addTokenEmComum(token);
		
		List<Integer> tiposDeToken = n1.getTiposDeTokenEmComum();
		for(Integer tipo : tiposDeToken)
			if(n2.contemTipoDeToken(tipo))
				addTipoDeTokenEmComum(tipo);
		
	}


}
