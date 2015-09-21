package crawler;

import java.util.concurrent.LinkedBlockingQueue;

import org.jsoup.nodes.Document;

import comum.PageDto;

public class Crawler {
	
	public Crawler(){
		
		LinkedBlockingQueue<PageDto> seeds = new LinkedBlockingQueue<PageDto>(CrawlerConfig.tamanhoBufferSeed);
		LinkedBlockingQueue<PageDto> documents = new LinkedBlockingQueue<PageDto>(CrawlerConfig.tamanhoBufferDoc);
		UrlsRecentementeVisitadas urlsVisited = new UrlsRecentementeVisitadas();
		
		for(int i = 0 ; i < CrawlerConfig.quantidadeDeWrappers ; i++){
			RunnableWrapper wr = new RunnableWrapper(documents, urlsVisited, i+1);
			Thread novaThread = new Thread(wr);
			novaThread.start();
		}
		
		for(int i = 0 ; i < CrawlerConfig.quantidadeDeDownloadsSimultaneos ; i++){
			RunnableDownload dr = new RunnableDownload(seeds, documents, urlsVisited, i+1);
			Thread novaThread = new Thread(dr);
			novaThread.start();
		}
		
		RunnableSeedCtrl sr = new RunnableSeedCtrl(seeds, urlsVisited, 1);
		Thread novaThread = new Thread(sr);
		novaThread.start();
		
		RunnableRemoveVisitados rvr = new RunnableRemoveVisitados(urlsVisited);
		Thread rvrThread = new Thread(rvr);
		rvrThread.start();
		
		
		
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			int seedsSize = seeds.size();
			int docsSize = documents.size();
			
			System.out.println(seedsSize + " / " + docsSize);
		}
		
		
	}
	
	public static void main(String[] args) {
		Crawler c = new Crawler();
	}

}
