package crawler;

import java.util.concurrent.LinkedBlockingQueue;

import wrapper.Wrapper;

import comum.PageDto;
import comum.UrlDto;

import database.EntityLista;
import database.EntityUrl;
import database.UpdateVisitStatus;

public class RunnableCrawlList implements Runnable {

	private LinkedBlockingQueue<PageDto> documents;
	private UrlsRecentementeVisitadas urlsVisited;
	private int id;
	private Wrapper w;
	private UrlColector c;
	
	public RunnableCrawlList(LinkedBlockingQueue<PageDto> documents, UrlsRecentementeVisitadas urlsVisited, int id){
		this.documents = documents;
		this.urlsVisited = urlsVisited;
		this.id = id;
		//this.w = new Wrapper();
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
		
		try {				
			
			//w.wrap(pageDto);
			EntityLista.inserirListasColetadas(pageDto);
			setUrlStatus(pageDto.getUrlDto(), VisitStatus.LINKS_COLETADOS);
			
		} catch (Throwable e) {
			
			setUrlStatus(pageDto.getUrlDto(), VisitStatus.PROBLEMA_PARSER);
			
		} finally {
			
			
			
		}
		
	}
	
	private void setUrlStatus(UrlDto urlDto, VisitStatus urlStatus){
		
		UpdateVisitStatus.doit(urlDto.getId(), urlStatus); 
		
	}

}
