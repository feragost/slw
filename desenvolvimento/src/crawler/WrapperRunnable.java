package crawler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

import jsoap.DocumentCreator;

import org.jsoup.nodes.Document;

import comum.PageDto;
import comum.UrlDto;
import database.CreateSql;
import database.Database;
import database.EntityUrl;
import database.SequenceCtrl;
import wrapper.Wrapper;

public class WrapperRunnable implements Runnable {
	
	private LinkedBlockingQueue<PageDto> documents;
	private UrlsRecentementeVisitadas urlsVisited;
	private int id;
	private Wrapper w;
	private UrlColector c;
	private SequenceCtrl seqCtrl;
	
	public WrapperRunnable(LinkedBlockingQueue<PageDto> documents, UrlsRecentementeVisitadas urlsVisited, int id){
		this.documents = documents;
		this.urlsVisited = urlsVisited;
		this.id = id;
		this.w = new Wrapper();
		this.c = new UrlColector();
		this.seqCtrl = SequenceCtrl.getInstance();
	}

	@Override
	public void run() {
		
		while(true){
			
			PageDto pageDto = null;
			
			try {
				
				//System.out.println("W" + id + " tentou pegar um novo doc");
				pageDto = documents.take();	
				//System.out.println("W" + id + " pegou um novo doc");
				
				c.coletar(pageDto);			
				w.wrap(pageDto);
				EntityUrl.inserirUrlColetadas(pageDto);
				//salvar listas aqui!
				setUrlStatus(pageDto.getUrlDto(), UrlStatus.VISITADA);
				
			} catch (Throwable e) {
				
				setUrlStatus(pageDto.getUrlDto(), UrlStatus.PROBLEMA_PARSER);
				
			} finally {
				
				urlsVisited.urlVisitada(pageDto.getUrlDto());
				
			}
			
			
		}
				
				
	}
	
	private void setUrlStatus(UrlDto urlDto, UrlStatus urlStatus){
		
		String sql = CreateSql.getSqlUpdateUrlStatus(urlDto.getId(), urlStatus); 
		Database.insert(sql);
		
	}
	
	
	
	

}
