package wrapper.agrup;

import java.util.List;

import wrapper.WrapperConfig;
import wrapper.comum.PageDto;
import wrapper.comum.Registro;

public class Agrupador {
	
	private PageDto page;
	
	public Agrupador(PageDto page){
		this.page = page;
	}
	
	/*
	 * Identificar listas com separadores e armazená-las.
	 * Se encontrar listas, deve-se retornar null.
	 * Se não encontrar listas, mas o número do registro ultrapassar um valor pré-determinado, deve-se retornar null. 
	 */
	public Registro agrupar(List<Registro> registros){
		
		if(registros.size() == 0){
			
			throw new RuntimeException("Zero registros foram entregues para agrupar");
			
		}else if(registros.size() > 0 && registros.size() < WrapperConfig.numeroMinimoDeRegistrosPorLista){	
			
			return Registro.compilar(registros);
			
		}else {
						
			Extrator extrator = new Extrator(WrapperConfig.numeroDeDerivacoes, WrapperConfig.numeroMinimoDeRegistrosPorLista);
			List<GrupoDeRegistrosSemelhantes> grupos = extrator.extrair(registros);
						
			if(grupos.size() == 0){
				
				return Registro.compilar(registros);
				
			}else{
				
				for(GrupoDeRegistrosSemelhantes grupo : grupos)
					page.addLista(grupo.getListDto());
				
				return null;
				
			}
			
			
		}
	}
	
	
	
	

}
