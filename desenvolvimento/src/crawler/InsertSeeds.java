package crawler;

import comum.PageDto;
import comum.UrlDto;
import database.EntityUrl;

public class InsertSeeds {
	
	public static void doit(){
		
		PageDto pageDto = new PageDto(null);
		for(UrlDto urlDto : CrawlerConfig.seeds){
			pageDto.addUrlColetada(urlDto);
		}
		
		EntityUrl.inserirUrlColetadas(pageDto);
		
	}
	
	
	public static void main(String[] args) {
		InsertSeeds.doit();
	}

}
