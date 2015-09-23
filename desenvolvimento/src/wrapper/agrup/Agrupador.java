package wrapper.agrup;

import java.util.List;

import comum.ListDto;
import comum.PageDto;
import comum.ValorQtdDto;
import comum.VisitDto;
import crawler.Settings;
import wrapper.WrapperConfig;
import wrapper.comum.Registro;

public class Agrupador {
	
	private PageDto page;
	private WrapperConfig wrapperConfig;
	
	public Agrupador(PageDto page){
		this.page = page;
		wrapperConfig = WrapperConfig.getInstance();
	}
	
	/*
	 * Identificar listas com separadores e armazená-las.
	 * Se encontrar listas, deve-se retornar null.
	 * Se não encontrar listas, mas o número do registro ultrapassar um valor pré-determinado, deve-se retornar null. 
	 */
	public Registro agrupar(List<Registro> registros){
		
		if(registros.size() == 0){
			
			throw new RuntimeException("Zero registros foram entregues para agrupar");
			
		}else if(registros.size() > 0 && registros.size() < wrapperConfig.getNumeroMinimoDeRegistrosPorLista()){	
			
			return Registro.compilar(registros);
			
		}else {
						
			Extrator extrator = new Extrator(wrapperConfig.getNumeroDeDerivacoes(), wrapperConfig.getNumeroMinimoDeRegistrosPorLista());
			List<GrupoDeRegistrosSemelhantes> grupos = extrator.extrair(registros);
						
			if(grupos.size() == 0){
				
				return Registro.compilar(registros);
				
			}else{
												
				for(GrupoDeRegistrosSemelhantes grupo : grupos){
					
					ListDto listDto = grupo.getListDto();
					
					if(validarListDto(listDto)){
						page.addLista(listDto);
					}
					
				}
				
				
				return null;
				
			}
			
			
		}
	}
	
	private boolean validarListDto(ListDto listDto){
		
		int contValidado = 0;
		int contNaoValidado = 0;
		
		for(ValorQtdDto vqtd : listDto.identsDesc){
			
			String desc = vqtd.getValor();
			desc = desc.substring(1, desc.length());			
			String[] descSplit = desc.split("\\.");
						
			boolean achouSegundoTexto = false;
			
			int i = 0;
			
			while(i < descSplit.length){
				if(descSplit[i].startsWith("x") || descSplit[i].startsWith("t")){	
					i++;
					break;
				}
				i++;
			}
			
			while(i < descSplit.length){
				if(descSplit[i].startsWith("s")){
					i++;
					break;
				}
				i++;
			}
			
			while(i < descSplit.length){
				if(descSplit[i].startsWith("x") || descSplit[i].startsWith("t")){
					achouSegundoTexto = true;
					i++;
					break;
				}
				i++;
			}
			
			if(achouSegundoTexto){
				contValidado += vqtd.getQtd();
			}else{
				contNaoValidado += vqtd.getQtd();
			}
			
			
		}
		
		//System.out.println(contValidado + " / " + contNaoValidado);
		
		return contValidado > contNaoValidado;
		
	}
	
	
	
	

}
