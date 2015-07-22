package wrapper.agrup;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import comum.IdValorDto;
import comum.ListDto;
import comum.ValorQtdDto;
import wrapper.comum.ElementoTexto;
import wrapper.comum.IdentItem;
import wrapper.comum.IdentSepItem;
import wrapper.comum.Identidade;
import wrapper.comum.Path;
import wrapper.comum.Registro;
import wrapper.comum.TipoToken;
import wrapper.comum.Token;

public class GrupoDeRegistrosSemelhantes {
	
	private List<Registro> registros;
	private List<Identidade> identidades;
	
	public GrupoDeRegistrosSemelhantes(List<Registro> registros){
		this.registros = registros;
		identidades = new LinkedList<Identidade>();
	}
	
	public void addIdentidade(Identidade identidade){
		identidades.add(identidade);
	}
	
	public List<Registro> getRegistros() {
		return registros;
	}
	
	public List<Identidade> getIdentidades(){
		return identidades;
	}
	
	public ListDto getListDto(){
		
		ListDto list = new ListDto();
		
		LinkedList<String> valoresRegistros = new LinkedList<String>();
		
		HashMap<Token, IdValorDto> separadores = new HashMap<Token, IdValorDto>();
		HashMap<Integer, IdValorDto> tiposToken = new HashMap<Integer, IdValorDto>();
		HashMap<Path, IdValorDto> paths = new HashMap<Path, IdValorDto>();
		
		HashMap<String, ValorQtdDto> idents = new HashMap<String, ValorQtdDto>();
		HashMap<String, ValorQtdDto> identsPath = new HashMap<String, ValorQtdDto>();
		
		for(Identidade ident : identidades){
			
			for(Token token : ident.getTokensEmComum()){
				if( ! separadores.containsKey(token)){
					String id = "s" + (separadores.size() + 1);
					IdValorDto idv = new IdValorDto(id, token.getValor());
					separadores.put(token, idv);
				}
			}
			
			
			TipoToken[] tiposTokenExistentes = TipoToken.values();
			for(Integer tipo : ident.getTiposDeTokenEmComum()){
				if( ! tiposToken.containsKey(tipo)){
					String id = "t" + (tiposToken.size() + 1);
					IdValorDto idv = new IdValorDto(id, tiposTokenExistentes[tipo].toString());
					tiposToken.put(tipo, idv);
				}
			}
			
			
		}
		
		for(Registro reg : registros){
			
			valoresRegistros.addLast(reg.getTexto());
			
			
			String identPath = "";
			String ident = "";
			boolean x = false;
			Path pathAtual = null;
			
			for(ElementoTexto et : reg.getElementoTexto()){
				
				Path etPath = et.getPath();
				
				if( ! paths.containsKey(etPath)){
					
					String id = "p" + (paths.size() + 1);
					
					String[] pathDesc = etPath.getPathDesc();
					String path = "";
					for(String pathItem : pathDesc)
						path += "/" + pathItem;
					
					IdValorDto idv = new IdValorDto(id, path);
					paths.put(etPath, idv);
					
				}
				
				if(etPath != pathAtual){
					IdValorDto idv = paths.get(et.getPath());
					identPath += "." + idv.getId();
					
					pathAtual = etPath;
				}
				
				
				
				
				
				for(Token token : et.getTokens()){
					
					
					if(tiposToken.containsKey(token.getTipo())){
						
						IdValorDto idv = tiposToken.get(token.getTipo());
						ident += "." + idv.getId();
						identPath += "." + idv.getId();
						x = false;
						
					} else 
					
					if(separadores.containsKey(token)){
						
						IdValorDto idv = separadores.get(token);
						ident += "." + idv.getId();
						identPath += "." + idv.getId();
						x = false;
						
					} else if( ! x ){
						
						ident += ".x";
						identPath += ".x";
						x = true;
						
					}
					
				}
				
				
				
				
				
				
			}						
			
			if( ! identsPath.containsKey(identPath) ){
				ValorQtdDto vqtd = new ValorQtdDto(identPath, 1);
				identsPath.put(identPath, vqtd);				
			}else{				
				ValorQtdDto vqtd = identsPath.get(identPath);
				vqtd.setQtd(vqtd.getQtd() + 1);		
			}
			
			if( ! idents.containsKey(ident) ){	
				ValorQtdDto vqtd = new ValorQtdDto(ident, 1);
				idents.put(ident, vqtd);				
			}else{				
				ValorQtdDto vqtd = idents.get(ident);
				vqtd.setQtd(vqtd.getQtd() + 1);
			}			
			
		}
		
		list.registros = valoresRegistros.toArray(new String[valoresRegistros.size()]);
		list.separadores = separadores.values().toArray(new IdValorDto[separadores.size()]);
		list.tiposEspeciais = tiposToken.values().toArray(new IdValorDto[tiposToken.size()]);
		list.paths = paths.values().toArray(new IdValorDto[paths.size()]);
		list.pathsDesc = identsPath.values().toArray(new ValorQtdDto[identsPath.size()]);
		list.identsDesc = idents.values().toArray(new ValorQtdDto[idents.size()]);		
		
		return list;
	}
		
	
	

}
