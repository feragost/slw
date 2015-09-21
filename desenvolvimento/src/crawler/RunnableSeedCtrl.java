package crawler;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

import comum.PageDto;
import comum.UrlDto;
import database.Database;

public class RunnableSeedCtrl implements Runnable {
	
	private LinkedBlockingQueue<PageDto> seeds;
	private UrlsRecentementeVisitadas urlsVisited;
	private int id;
	
	public RunnableSeedCtrl(LinkedBlockingQueue<PageDto> seeds, UrlsRecentementeVisitadas urlsVisited, int id) {
		this.seeds = seeds;
		this.urlsVisited = urlsVisited;
		this.id = id;
	}

	@Override
	public void run() {
		
		while(true){
			List<UrlDto> urlDtos = getUrlDtos();
			for(UrlDto urlDto : urlDtos){
				
				PageDto pageDto = new PageDto(urlDto);
				
				try {
					seeds.put(pageDto);
				} catch (InterruptedException e) {
					e.printStackTrace();					
				}
				
			}		
			
		}
		
		
		
	}
	
	private List<UrlDto> getUrlDtos(){
		
		LinkedList<UrlDto> urlDtos = new LinkedList<UrlDto>();
		
		String sql = "SELECT * FROM tb_url WHERE id_url_status = " + VisitStatus.NOVA_URL.getId() + " ORDER BY RAND() LIMIT 200";
		String[][] matrix = Database.getMatrizOf(sql);
		
		for(String[] reg : matrix){
			
			long id = Long.parseLong(reg[0]);
			long idAuth = Long.parseLong(reg[1]);
			String url = reg[2];
			
			UrlDto urlDto = new UrlDto(url, id, idAuth);
			urlDtos.addLast(urlDto);
			
		}
		
		List<UrlDto> urlDtosFiltrados = urlsVisited.filterUrls(urlDtos);
		System.out.println("urlDtosFiltrados.size(): " + urlDtosFiltrados.size());
		return urlDtosFiltrados;
		
	}
	
	
	

}
