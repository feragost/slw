package crawler;

import java.util.concurrent.LinkedBlockingQueue;
import comum.PageDto;
import database.EntityUrl;

public class RunnableCrawlUrl implements Runnable {

	private LinkedBlockingQueue<PageDto> documents;
	private UrlsRecentementeVisitadas urlsVisited;
	private int id;
	private UrlColector c;
	
	public RunnableCrawlUrl(LinkedBlockingQueue<PageDto> documents, UrlsRecentementeVisitadas urlsVisited, int id){
		this.documents = documents;
		this.urlsVisited = urlsVisited;
		this.id = id;
		this.c = new UrlColector();
	}
	
	public int getId(){
		return this.id;
	}

	@Override
	public void run() {
		
		
		while(true){
			
			PageDto pageDto = null;
			
			try {
				
				pageDto = documents.take();
				workWith(pageDto);
				
			}catch (Throwable e) {
								
			}finally{
				
				urlsVisited.urlVisitada(pageDto.getUrlDto());
				
			}
			
		}
				
				
	}
	
	private void workWith(PageDto pageDto){
		
		try {
			
			c.coletar(pageDto);		
			EntityUrl.inserirUrlColetadas(pageDto);
							
		} catch (Throwable e) {
							
		}
		
		
	}
		

}
