package crawler;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import comum.PageDto;

public abstract class RunnableSeedsCtrl implements Runnable {
	
	protected abstract List<PageDto> getPageDtos();
	
	private LinkedBlockingQueue<PageDto> seeds;
	protected UrlsRecentementeVisitadas urlsVisited;
	private int quantidade;
	
	public RunnableSeedsCtrl(LinkedBlockingQueue<PageDto> seeds, UrlsRecentementeVisitadas urlsVisited, int qtd) {
		this.seeds = seeds;
		this.urlsVisited = urlsVisited;
		this.quantidade = qtd;
	}
	
	public int getQuantidade(){
		return this.quantidade;
	}
	
	@Override
	public void run() {
		
		while(quantidade > 0){
			List<PageDto> pageDtos = getPageDtos();
			for(PageDto pageDto : pageDtos){
				
				quantidade--;
								
				try {
					seeds.put(pageDto);
				} catch (InterruptedException e) {
					e.printStackTrace();					
				}
				
				if(quantidade <= 0){
					break;
				}
				
			}		
			
		}		
		
	}
	
	

}
