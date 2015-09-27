package main;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import jsoap.DocumentCreator;

import org.apache.commons.io.FileUtils;
import org.jsoup.nodes.Document;

import showresult.ShowResult;
import wrapper.Wrapper;
import comum.ListDto;
import comum.PageDto;
import comum.UrlDto;
import comum.VisitDto;
import crawler.Settings;

public class Revoc {
	
	public static void main(String[] args) {
		Revoc.wrap();
	}
	
	public static void wrap(){
		
		Wrapper[] ws;
		
		Settings[] settingsValues = Settings.values();
		ws = new Wrapper[settingsValues.length];
		for(int i = 0 ; i < settingsValues.length ; i++){
			ws[i] = new Wrapper(settingsValues[i]);
		}
		
		List<PageDto> pageDtos = Revoc.getPageDtos();
		
		int numVisitas = 0;
		int numVisitasMax = pageDtos.size();
		
		for(PageDto pageDto : pageDtos){
			
			numVisitas++;
			System.out.println(numVisitas + "/" + numVisitasMax + " : " + pageDto.getUrlDto().getUrl());
			
			Document doc = DocumentCreator.create(pageDto.getUrlDto().getUrl());
			
			try{
				
				VisitDto[] visitDtos = new VisitDto[ws.length];
								
				for(int i = 0 ; i < ws.length ; i++){
					
					Wrapper w = ws[i];
					
					PageDto pageToWrap = new PageDto(pageDto.getUrlDto());
					pageToWrap.setDoc(doc);
									
					w.wrap(pageToWrap);
					
					VisitDto visitDto = new VisitDto(i + 1, w.getSettings());
					
					for(ListDto listDto : pageToWrap.getListas()){
						visitDto.addLista(listDto);						
					}
					
					visitDtos[i] = visitDto;					
					
				}
				
				
				ShowResult.putInRevocResults(pageDto.getUrlDto(), visitDtos);
				
			}catch(Throwable t){
				
				t.printStackTrace();
				
			}					
			
		}
		
	}
	
	public static List<PageDto> getPageDtos() {
		
		LinkedList<UrlDto> urlDtos = new LinkedList<UrlDto>();
		
		File file = new File("revoc_links.txt"); 
		List<String> lista = null;
		try {
			lista = FileUtils.readLines(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int id = 0;
		for(String link : lista){
			
			UrlDto urlDto = new UrlDto(link, ++id, 1);			
			urlDtos.add(urlDto);
			
		}
		
		List<PageDto> pageDtos = new LinkedList<PageDto>();
		for(UrlDto urlDto : urlDtos){
			pageDtos.add(new PageDto(urlDto));
		}
		
		return pageDtos;
		
	}
	
}
