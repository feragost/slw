package crawler;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

import jsoap.DocumentCreator;

import org.jsoup.nodes.Document;

import comum.PageDto;
import comum.UrlDto;
import database.CreateSql;
import database.Database;

public class DownloadRunnable implements Runnable {
	
	private LinkedBlockingQueue<PageDto> seeds;
	private LinkedBlockingQueue<PageDto> documents;
	private UrlsRecentementeVisitadas urlsVisited;
	private int id;
	
	public DownloadRunnable(LinkedBlockingQueue<PageDto> seeds, LinkedBlockingQueue<PageDto> documents, UrlsRecentementeVisitadas urlsVisited, int id){
		
		this.seeds = seeds;
		this.documents = documents;
		this.urlsVisited = urlsVisited;
		this.id = id;
		
	}
	
	
	@Override
	public void run(){	
		
		while(true){
			
			PageDto pageDto = null;
			
			try {
				pageDto = seeds.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			
			Document doc = DocumentCreator.create(pageDto.getUrlDto().getUrl());
			System.out.println("D" + id + "fez download de " + pageDto.getUrlDto().getUrl());
			
			if(doc == null){
				
				setUrlProblema(pageDto.getUrlDto());
				urlsVisited.urlVisitada(pageDto.getUrlDto());
				
			}else{
				
				try {
					pageDto.setDoc(doc);
					documents.put(pageDto);
				} catch (InterruptedException e) {
					e.printStackTrace();									
				}
				
			}			
						
		}
		
	}
	
	private void setUrlProblema(UrlDto urlDto){
				
		String sql = CreateSql.getSqlUpdateUrlStatus(urlDto.getId(), UrlStatus.PROBLEMA_DOWN); 
		Database.insert(sql);
		
	}

}
