package database;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import jsoap.DocumentCreator;

import org.jsoup.nodes.Document;

import comum.PageDto;
import comum.UrlDto;
import crawler.CrawlerConfig;
import crawler.UrlColector;
import crawler.VisitStatus;

public class EntityUrl {
	
	public static synchronized void inserirUrlColetadas(PageDto pageDto){
		
		SequenceCtrl seqCtrl = SequenceCtrl.getInstance();
		HashMap<String, Long> hashAuth = EntityAuth.insert(pageDto);		
		LinkedList<String> sqls = new LinkedList<String>();
		
		UrlDto[] urlDtos = pageDto.getUrlColetadas();
		HashSet<String> hashUrlsJaInseridas = getUrlsJaInseridas(urlDtos);		
		
		for(UrlDto urlDto : urlDtos){
			
			if( ! hashUrlsJaInseridas.contains(urlDto.getUrl()) ){
				
				long id = seqCtrl.getNextIdUrl();
				long idAuth = hashAuth.get(urlDto.getAuth());
				String descricao = urlDto.getUrl();
				descricao = descricao.replaceAll("'", "''");
				int idUrlStatus = VisitStatus.NOVA_URL.getId();
				int idWrapperStatus = VisitStatus.NOVA_URL.getId();
				
				
				String sql = "INSERT INTO tb_url VALUES (" + id + "," + idAuth + ",'" + descricao + "'," + idUrlStatus + ","+idWrapperStatus+")";
				sqls.add(sql);
				
			}			
			
		}
		
		Database.insert(sqls);
		
	}
	
	private static HashSet<String> getUrlsJaInseridas(UrlDto[] urlDtos){
		
		HashSet<String> hashUrlsJaInseridas = new HashSet<String>();
		
		String sql = "SELECT descricao FROM tb_url WHERE descricao IN ( ? )";
		String x = "";
		for(UrlDto urlDto : urlDtos){
			String descricao = urlDto.getUrl().replaceAll("'", "''");
			x += ",'" + descricao + "'";
		}
		x = x.replaceFirst(",", "");
		sql = sql.replace("?", x);
				
		String[][] result = Database.getMatrizOf(sql);
		for(String[] reg : result){
			hashUrlsJaInseridas.add(reg[0]);
		}		
		
		return hashUrlsJaInseridas;
	}
	
	public static void main(String[] args) {
		
		//Schema.doit();
		
		UrlColector urlColector = new UrlColector();
		
		UrlDto urlDto = CrawlerConfig.seeds[4];
		Document doc = DocumentCreator.create(urlDto.getUrl());
		PageDto pageDto = new PageDto(urlDto);
		pageDto.setDoc(doc);
		urlColector.coletar(pageDto);
		
		EntityUrl.inserirUrlColetadas(pageDto);
		
		
	}

}
