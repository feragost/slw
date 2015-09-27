package database;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import comum.IdValorDto;
import comum.ListDto;
import comum.PageDto;
import comum.RegistroDto;
import comum.ValorQtdDto;
import crawler.Settings;

public class EntityLista {
	
	public static synchronized void inserirListasColetadas(PageDto pageDto, Settings settings){
				
		inserirVisita(pageDto, settings);
				
	}
	
	private static void inserirVisita(PageDto pageDto, Settings settings){
		
		SequenceCtrl seqCtrl = SequenceCtrl.getInstance();
		
		List<ListDto> listasDto = pageDto.getListas();
		
		LinkedList<String> sqls = new LinkedList<String>();
		
		long idVisit = seqCtrl.getNextIdVisita();
		long idUrl = pageDto.getUrlDto().getId();
		int idSett = settings.getId();
		String sqlVisit = "INSERT INTO tb_visit VALUES("+idVisit+","+idUrl+","+idSett+")";
		sqls.addLast(sqlVisit);
		
		for(ListDto listDto : listasDto){
			
			String jsonString = getJsonDe(listDto);
			System.out.println(jsonString);
			
			if(jsonString != null){
				
				long id = seqCtrl.getNextIdLista();
				
				jsonString = jsonString.replaceAll("'", "''");
				
				String sql = "INSERT INTO tb_lista VALUES (" + id + "," + idVisit + ",'" + jsonString + "')";
				sqls.addLast(sql);
				
			}
			
		}
		
		Database.insert(sqls);
		
	}
	
	private static String getJsonDe(ListDto listDto){
		
		String[] registros = listDto.registros;
		JSONArray regsJson = new JSONArray();
		for(String registro : registros){
			JSONObject regJson = new JSONObject();
			regJson.put("desc", registro);
			regsJson.put(regJson);
		}
		
		RegistroDto[] registrosDtos = listDto.registroDtos;
		JSONArray regsDtosJson = new JSONArray();
		for(RegistroDto regDto : registrosDtos){
			
			JSONArray attsJson = new JSONArray();
			String[] atts = regDto.getAtributos();
			for(String att : atts){
				JSONObject attJson = new JSONObject();
				attJson.put("att", att);
				attsJson.put(attJson);
			}
			regsDtosJson.put(attsJson);
		}
		
		
		JSONArray sepsJson = new JSONArray();		
		IdValorDto[] separadores = listDto.separadores;
		for(IdValorDto separador : separadores){
			JSONObject sepJson = new JSONObject();
			sepJson.put("id", separador.getId());
			sepJson.put("desc", separador.getValor());
			sepsJson.put(sepJson);
		}
		
				
		
		
		JSONArray identsJson = new JSONArray();
		ValorQtdDto[] idents = listDto.identsDesc;
		for(ValorQtdDto ident : idents){
			JSONObject identJson = new JSONObject();
			identJson.put("desc", ident.getValor());
			identJson.put("qtd", ident.getQtd());
			identsJson.put(identJson);
		}
		
		
		
		
		JSONObject listJson = new JSONObject();
		listJson.put("regstext", regsJson);
		listJson.put("regs", regsDtosJson);
		listJson.put("seps", sepsJson);
		listJson.put("idents", identsJson);
				
		
		return listJson.toString();
		
	}
	
	

}
