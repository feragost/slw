package database;

import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;

import jsoap.DocumentCreator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.nodes.Document;

import wrapper.Wrapper;
import comum.IdValorDto;
import comum.ListDto;
import comum.PageDto;
import comum.UrlDto;
import comum.ValorQtdDto;
import comum.VisitDto;
import crawler.CrawlerConfig;
import crawler.UrlColector;

public class EntityLista {
	
	public static synchronized void inserirListasColetadas(PageDto pageDto){
		
		SequenceCtrl seqCtrl = SequenceCtrl.getInstance();
		
		List<VisitDto> visitDtos = pageDto.getVisits();
		
		for(VisitDto visitDto : visitDtos){
			inserirVisita(pageDto.getUrlDto(), visitDto);
		}
		
		
		
	}
	
	private static void inserirVisita(UrlDto urlDto, VisitDto visitDto){
		
		SequenceCtrl seqCtrl = SequenceCtrl.getInstance();
		
		List<ListDto> listasDto = visitDto.getListas();
		
		LinkedList<String> sqls = new LinkedList<String>();
		
		long idVisit = seqCtrl.getNextIdVisita();
		long idUrl = urlDto.getId();
		int idSett = visitDto.getSettings().getId();
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
		
		
		JSONArray sepsJson = new JSONArray();		
		IdValorDto[] separadores = listDto.separadores;
		for(IdValorDto separador : separadores){
			JSONObject sepJson = new JSONObject();
			sepJson.put("id", separador.getId());
			sepJson.put("desc", separador.getValor());
			sepsJson.put(sepJson);
		}
		
		JSONArray tiposJson = new JSONArray();		
		IdValorDto[] tipos = listDto.tiposEspeciais;
		for(IdValorDto tipo : tipos){
			JSONObject tipoJson = new JSONObject();
			tipoJson.put("id", tipo.getId());
			tipoJson.put("desc", tipo.getValor());
			tiposJson.put(tipoJson);
		}
		
		JSONArray pathsJson = new JSONArray();		
		IdValorDto[] paths = listDto.paths;
		for(IdValorDto path : paths){
			JSONObject pathJson = new JSONObject();
			pathJson.put("id", path.getId());
			pathJson.put("desc", path.getValor());
			pathsJson.put(pathJson);
		}
		
		
		
		JSONArray identsJson = new JSONArray();
		ValorQtdDto[] idents = listDto.identsDesc;
		for(ValorQtdDto ident : idents){
			JSONObject identJson = new JSONObject();
			identJson.put("desc", ident.getValor());
			identJson.put("qtd", ident.getQtd());
			identsJson.put(identJson);
		}
		
		JSONArray identsPathJson = new JSONArray();
		ValorQtdDto[] identsPath = listDto.pathsDesc;
		for(ValorQtdDto identPath : identsPath){
			JSONObject identPathJson = new JSONObject();
			identPathJson.put("desc", identPath.getValor());
			identPathJson.put("qtd", identPath.getQtd());
			identsPathJson.put(identPathJson);
		}
		
		
		JSONObject listJson = new JSONObject();
		listJson.put("regs", regsJson);
		listJson.put("seps", sepsJson);
		listJson.put("tipos", tiposJson);
		listJson.put("paths", pathsJson);
		listJson.put("idents", identsJson);
		listJson.put("identsPath", identsPathJson);
				
		
		return listJson.toString();
		
	}
	
	public static void main(String[] args) {
						
		UrlDto urlDto = CrawlerConfig.seeds[3];
		Document doc = DocumentCreator.create(urlDto.getUrl());
		PageDto pageDto = new PageDto(urlDto);
		pageDto.setDoc(doc);
		//Wrapper w = new Wrapper();
		//w.wrap(pageDto);
		
		EntityLista.inserirListasColetadas(pageDto);
	}

}
