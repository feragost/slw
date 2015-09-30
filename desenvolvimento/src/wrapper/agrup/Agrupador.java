package wrapper.agrup;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import comum.ListDto;
import comum.PageDto;
import comum.RegistroDto;
import comum.ValorQtdDto;
import comum.VisitDto;
import crawler.Settings;
import wrapper.WrapperConfig;
import wrapper.comum.Registro;
import wrapper.separar.Separador;

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
						
						Separador sep = new Separador();
						sep.separar(listDto);
						
						if(validarSeparacao(listDto)){
							page.addLista(listDto);							
						}
						
					}
					
				}
				
				
				return null;
				
			}
			
			
		}
	}
	
	private boolean validarSeparacao(ListDto listDto){
		
		RegistroDto[] registrosDtos = listDto.registroDtos;
		int numAtts = listDto.getIdentComMaiorQuantidade().getIdent().length + 1;
		
		boolean[] attsVazios = new boolean[numAtts];
		for(int i = 0 ; i < attsVazios.length ; i++){
			attsVazios[i] = true;
		}
		
		HashSet<String>[] attsValores = new HashSet[numAtts];
		for(int i = 0 ; i < attsValores.length ; i++){
			attsValores[i] = new HashSet<String>();
		}
		
		TextosColuna[] textosColuna = new TextosColuna[numAtts];
		for(int i = 0 ; i < textosColuna.length ; i++){
			textosColuna[i] = new TextosColuna();
		}
		
		for(RegistroDto registroDto : registrosDtos){
			
			String[] atts = registroDto.getAtributos();
			
			if(atts != null && atts.length > 0){
				
				for(int i = 0 ; i < atts.length ; i++){					
					
					if(atts[i] != null && !atts[i].trim().equals("")){
						attsVazios[i] = false;
						attsValores[i].add(atts[i]);
						textosColuna[i].addTexto(atts[i]);
					}					
					
				}
				
			}
			
		}
		
		HashSet<Integer> indexOut = new HashSet<Integer>();
		
		for(int i = 0 ; i < numAtts ; i++){
			if(attsVazios[i] || attsValores[i].size() < 2){
				indexOut.add(i);
			}else if(textosColuna[i].getMaiorQuantidade() > wrapperConfig.getPercentualMaximoParaRepeticaoEmColuna() * registrosDtos.length){
				indexOut.add(i);
			}
		}
		
		
		
		if(indexOut.size() > 0){
			
			for(RegistroDto registroDto : registrosDtos){
				
				String[] atts = registroDto.getAtributos();
				
				if(atts != null && atts.length > 0){
					
					LinkedList<String> attsNovos = new LinkedList<String>();					
					for(int i = 0 ; i < atts.length ; i++){
						if(!indexOut.contains(i)){
							attsNovos.addLast(atts[i]);
						}
					}
					
					registroDto.addAtributos(attsNovos.toArray(new String[attsNovos.size()]));
					
				}
				
				
				
			}
			
		}
		
		if(numAtts - indexOut.size() < 2){
			return false;
		}		
		
		return true;
	}
	
	
	
	private boolean validarListDto(ListDto listDto){
		
		
		int qtdRegistros = listDto.registros.length;
		int maxIdent = listDto.getIdentComMaiorQuantidade().getQtd();		
		float minAceito = qtdRegistros * wrapperConfig.getPercentualMinimoParaQuantidadeDeIdent();
				
		if(maxIdent < minAceito){
			return false;
		}
		
		
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
	
	
	public class TextosColuna{
		
		HashMap<String, Integer> hashTextoColuna;
		
		public TextosColuna(){
			hashTextoColuna = new HashMap<String, Integer>();
		}
		
		public void addTexto(String texto){
			
			if(! hashTextoColuna.containsKey(texto)){
				hashTextoColuna.put(texto, 0);
			}
			
			int qtd = hashTextoColuna.remove(texto);
			hashTextoColuna.put(texto, qtd + 1);
			
		}
		
		public int getMaiorQuantidade(){
			Collection<Integer> values = hashTextoColuna.values();
			int maior = 0;
			for(Integer i : values){
				if(i > maior){
					maior = i;
				}
			}
			return maior;
		}
		
	}
	

}
