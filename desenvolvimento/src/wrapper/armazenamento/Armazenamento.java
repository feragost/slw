package wrapper.armazenamento;

import java.util.List;

import wrapper.agrup.GrupoDeRegistrosSemelhantes;
import wrapper.agrup.Identidade;
import wrapper.comum.PageDto;
import wrapper.parser.Registro;
import wrapper.parser.TipoToken;
import wrapper.tokenizador.Token;

public class Armazenamento {
	
	public Armazenamento(){
		
	}
	
	public void armazenar(PageDto page){
		
		TipoToken[] valoresTipoToken = TipoToken.values();
				
		for(GrupoDeRegistrosSemelhantes grupo : page.getGrupos()){
			
			System.out.println("--------grupo--------");
			
			List<Registro> registros = grupo.getRegistros();
			for(Registro reg : registros)
				System.out.println(reg.getTexto());
			
			
			
			List<Identidade> identidades = grupo.getIdentidades();
			for(Identidade identidade : identidades){
				
				System.out.println("--------ident--------");
				
				Token[] tokens = identidade.getTokensEmComum();
				for(Token token : tokens){
					System.out.println("token  " + token.getValor());
				}
				
				Integer[] tipos = identidade.getTiposDeTokenEmComum();
				for(Integer tipo : tipos){
					System.out.println("tipo  " + valoresTipoToken[tipo]);
				}
				
				
			}
			
			System.out.println("");
			
		}
		
	}

}
