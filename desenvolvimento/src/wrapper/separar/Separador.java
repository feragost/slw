package wrapper.separar;

import java.util.List;

import wrapper.comum.Token;
import comum.ListDto;
import comum.RegistroDto;

public class Separador {

	public void Separador() {

	}

	public void separar(ListDto listDto) {

		Token[] ident = listDto.getIdentComMaiorQuantidade().getIdent();
		
		RegistroDto[] registrosDtos = listDto.registroDtos;

		for (RegistroDto registroDto : registrosDtos) {
											
			String[] atributos = new String[ident.length + 1];			

			Token[] tokens = registroDto.getTokens();			
			
			int indexIdent = 0;
			boolean completo = false;
			String atributoAtual = "";

			for (int indexTokens = 0; indexTokens < tokens.length; indexTokens++) {
				
				if(tokens[indexTokens] == ident[indexIdent]){
					
					atributos[indexIdent] = atributoAtual.trim();
					atributoAtual = "";
					
					indexIdent ++;
					
					if(indexIdent == ident.length){
						
						String ultAtributo = "";
						
						for(int i = indexTokens + 1 ; i < tokens.length ; i++){
							ultAtributo += tokens[i].getValor() + " ";
						}
						
						atributos[indexIdent] = ultAtributo.trim();
						completo = true;						
						break;
					}
					
				}else{
					atributoAtual += tokens[indexTokens].getValor() + " ";
				}

			}
			
			if(completo){
				registroDto.addAtributos(atributos);
			}

		}
		

	}
	
	
	

}
