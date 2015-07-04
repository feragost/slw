package wrapper.agrup;

import java.util.LinkedList;
import java.util.List;

import wrapper.WrapperConfig;
import wrapper.parser.ElementoTexto;
import wrapper.parser.Registro;

public class Agrupador {
	
	public Agrupador(){
		
	}
	
	/*
	 * Identificar listas com separadores e armazená-las.
	 * Se encontrar listas, deve-se retornar null.
	 * Se não encontrar listas, mas o número do registro ultrapassar um valor pré-determinado, deve-se retornar null. 
	 */
	public Registro agrupar(List<Registro> registros){
		
		if(registros.size() > 0){
			
			throw new RuntimeException("Zero registros foram entregues para agrupar");
			
		}else if(registros.size() > 0 && registros.size() < WrapperConfig.numeroMinimoDeRegistros){	
			
			return Registro.compilar(registros);			
			
		}else {
			
			
			
		}
		
		
		
		
		
		return null;
	}
	
	
	
	

}
