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
			WrapperRunnable wr = new WrapperRunnable(documents, urlsVisited, i+1);
			Thread novaThread = new Thread(wr);
			novaThread.start();
		}
		
		for(int i = 0 ; i < CrawlerConfig.quantidadeDeDownloadsSimultaneos ; i++){
			DownloadRunnable dr = new DownloadRunnable(seeds, documents, urlsVisited, i+1);
			Thread novaThread = new Thread(dr);
			novaThread.start();
		}
		
		SeedCtrlRunnable sr = new SeedCtrlRunnable(seeds, urlsVisited, 1);
		Thread novaThread = new Thread(sr);
		novaThread.start();
		
		RemoveVisitadosRunnable rvr = new RemoveVisitadosRunnable(urlsVisited);
		Thread rvrThread = new Thread(rvr);
		rvrThread.start();
		
		
		
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
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
