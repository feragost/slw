package crawler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;

import jsoap.DocumentCreator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import comum.PageDto;
import comum.UrlDto;

public class UrlColector {
	
	public void coletar(PageDto pageDto){
		
		Document doc = pageDto.getDoc();
		
		Elements links = doc.select("a");
		Iterator<Element> it = links.iterator();
		
		
		while(it.hasNext()){
			String stringUrl = it.next().attr("abs:href");
			try {
				URL novaUrl = new URL(stringUrl);								
				UrlDto urlDto = new UrlDto(novaUrl.getAuthority(), novaUrl.toString());
				pageDto.addUrlColetada(urlDto);			
				
			} catch (MalformedURLException e) {
				
			}			
		}
		
	}
	
	public static void main(String[] args) {
		
		UrlColector urlColector = new UrlColector();
		
		UrlDto urlDto = CrawlerConfig.seeds[3];
		Document doc = DocumentCreator.create(urlDto.getUrl());
		PageDto pageDto = new PageDto(urlDto);
		pageDto.setDoc(doc);
		urlColector.coletar(pageDto);
	}

}
