package showresult;

import org.json.JSONArray;
import org.json.JSONObject;

import comum.IdValorDto;
import comum.ListDto;
import comum.RegistroDto;
import comum.UrlDto;
import comum.ValorQtdDto;
import comum.VisitDto;

import crawler.Settings;
import database.Database;

public class MontarVisitas {
	
public static VisitDto[] doit(UrlDto urlDto){
		
		String sql = "SELECT id, id_settings from tb_visit WHERE id_url = " + urlDto.getId();
		String[][] matrix = Database.getMatrizOf(sql);
		
		VisitDto[] visitDtos = new VisitDto[matrix.length];
		
		for(int i = 0 ; i < matrix.length ; i++){
			long id = Long.parseLong(matrix[i][0]);
			int idSettings = Integer.parseInt(matrix[i][1]);
			Settings settings = Settings.getSettings(idSettings);
			VisitDto visit = new VisitDto(id, settings);
			
			visitDtos[i] = visit;
		}
		
		for(VisitDto visitDto : visitDtos){
			
			ListDto[] listDtos = getListas(visitDto);
			for(ListDto listDto : listDtos){
				visitDto.addLista(listDto);				
			}
			
		}
		
		
		return visitDtos;
		
	}
	
	public static ListDto[] getListas(VisitDto visitDto){
		
		String sql = "SELECT content FROM tb_lista WHERE id_visit = " + visitDto.getId();
		String[][] matrix = Database.getMatrizOf(sql);
		
		ListDto[] listDtos = new ListDto[matrix.length];
		
		for(int i = 0 ; i < matrix.length ; i++){
			
			JSONObject listJson = new JSONObject(matrix[i][0]);
			listDtos[i] = getListDto(listJson);
			
		}
		
		return listDtos;
		
	}
	
	public static ListDto getListDto(JSONObject listJson){
		
		ListDto listDto = new ListDto();
		//System.out.println(listJson.toString());
		
		JSONArray regstext = listJson.getJSONArray("regstext");
		JSONArray regs = listJson.getJSONArray("regs");
		JSONArray seps = listJson.getJSONArray("seps");
		JSONArray idents = listJson.getJSONArray("idents");
				
		listDto.registros = getRegistros(regstext);
		listDto.registroDtos = getRegistrosDto(regs);
		listDto.separadores = getSeparadores(seps);
		listDto.identsDesc = getIdentidades(idents);
		
		return listDto;
	}
	
	public static String[] getRegistros(JSONArray regstext){
		String[] registros = new String[regstext.length()];
		for(int i = 0 ; i < registros.length ; i++){
			JSONObject regJson = regstext.getJSONObject(i);
			String desc = regJson.getString("desc");
			registros[i] = desc;
		}
		return registros;
	}
	
	public static RegistroDto[] getRegistrosDto(JSONArray regs){
		
		RegistroDto[] registroDtos = new RegistroDto[regs.length()];
		
		for(int i = 0 ; i < registroDtos.length ; i++){
			
			RegistroDto registroDto = new RegistroDto();
			
			JSONArray attsJson = regs.getJSONArray(i);
			String[] atributos = new String[attsJson.length()];
			for(int j = 0 ; j < atributos.length ; j++){
				JSONObject attJson = attsJson.getJSONObject(j);
				atributos[j] = attJson.getString("att");
			}
			
			registroDto.addAtributos(atributos);
			
			registroDtos[i] = registroDto;
			
		}
				
		return registroDtos;
	}
	
	public static IdValorDto[] getSeparadores(JSONArray seps){
		
		IdValorDto[] separadores = new IdValorDto[seps.length()];
		for(int i = 0 ; i < separadores.length ; i++){
			JSONObject sepJson = seps.getJSONObject(i);
			String id = sepJson.getString("id");
			String desc = sepJson.getString("desc");
			IdValorDto idv = new IdValorDto(id, desc);
			separadores[i] = idv;
		}
		
		return separadores;
	}
	
	public static ValorQtdDto[] getIdentidades(JSONArray idents){
		
		ValorQtdDto[] identidades = new ValorQtdDto[idents.length()];
		for(int i = 0 ; i < identidades.length ; i++){
			JSONObject identJson = idents.getJSONObject(i);
			String desc = identJson.getString("desc");
			int qtd = identJson.getInt("qtd");
			ValorQtdDto vqtd = new ValorQtdDto(desc, qtd);
			identidades[i] = vqtd;
		}
		
		return identidades;
	}

}
