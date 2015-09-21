package crawler;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import comum.UrlDto;

public class UrlsRecentementeVisitadas {
	
	private HashSet<Long> hashUrlsIndicadas;
	
	private HashSet<Long> hashUrlsVisitadas;
	private LinkedList<MapAuthTime> listAuthTime;
	
	public UrlsRecentementeVisitadas(){
		
		hashUrlsIndicadas = new HashSet<Long>();
		hashUrlsVisitadas = new HashSet<Long>();
		listAuthTime = new LinkedList<MapAuthTime>();
		
	}
	
	public synchronized List<UrlDto> filterUrls(List<UrlDto> urlDtos){
		
		List<UrlDto> urlsFiltradas = new LinkedList<UrlDto>();
		
		for(UrlDto urlDto : urlDtos){
			
			long idAuth = urlDto.getIdAuth();
			
			if( ! hashUrlsIndicadas.contains(idAuth) && ! hashUrlsVisitadas.contains(idAuth)){
				urlsFiltradas.add(urlDto);
				hashUrlsIndicadas.add(idAuth);
			}
						
		}
		
		
		return urlsFiltradas;
	}
	
	/*
	public synchronized void removeUrlIndicada(UrlDto urlDto){
		hashUrlsIndicadas.remove(urlDto.getIdAuth());
	}
	*/
	
	public synchronized void urlVisitada(UrlDto urlDto){
		
		long timeMillisAtual = System.currentTimeMillis();
		long idAuth = urlDto.getIdAuth();
		
		hashUrlsIndicadas.remove(idAuth);
		hashUrlsVisitadas.add(idAuth);
		
		MapAuthTime map = new MapAuthTime(idAuth, timeMillisAtual);
		listAuthTime.addLast(map);
		
	}
	
	public synchronized void removeVisited(long timeMillis){
		
		do{
			
			if(listAuthTime.size() == 0){
				break;
			}
			
			MapAuthTime map = listAuthTime.getFirst();
			if(map.getTimeMillis() < timeMillis){
				MapAuthTime removed = listAuthTime.removeFirst();
				hashUrlsVisitadas.remove(map.getAuth());
				System.out.println(removed.getAuth() + " removido");
			}else{
				break;
			}
			
		}while(listAuthTime.size() > 0);
				
		
	}
	
	
	


}
