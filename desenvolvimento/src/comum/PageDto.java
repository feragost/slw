package comum;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Document;

import wrapper.agrup.GrupoDeRegistrosSemelhantes;

public class PageDto {
	
	private Document doc;
	
	private UrlDto urlDto;
	private List<UrlDto> urlsColetadas;
	
	private List<VisitDto> visitDtos;
	
	
	
	public PageDto(UrlDto urlDto){
		this.urlDto = urlDto;
		//this.listas = new LinkedList<ListDto>();
		this.urlsColetadas = new LinkedList<UrlDto>();
		this.visitDtos = new LinkedList<VisitDto>();
	}
	
	public PageDto(){
		this.urlDto = null;
		this.visitDtos = null;
		this.doc = null;
		this.urlsColetadas = null;
	}
	
	public void addVisit(VisitDto visitDto){
		this.visitDtos.add(visitDto);
	}
	
	public List<VisitDto> getVisits(){
		return this.visitDtos;
	}
	
	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}
	
	public void addUrlColetada(UrlDto urlDto){
		this.urlsColetadas.add(urlDto);
	}

	public UrlDto getUrlDto() {
		return urlDto;
	}

	/*
	public void addLista(ListDto listDto){
		this.listas.add(listDto);
	}

	public List<ListDto> getListas() {
		return listas;
	}
	*/
	
	public UrlDto[] getUrlColetadas(){
		return urlsColetadas.toArray(new UrlDto[urlsColetadas.size()]);
	}
	

}
