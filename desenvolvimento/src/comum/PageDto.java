package comum;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Document;

import wrapper.agrup.GrupoDeRegistrosSemelhantes;

public class PageDto {
	
	private Document doc;
	
	private UrlDto urlDto;
	private List<UrlDto> urlsColetadas;
	private List<ListDto> listas;
	
	
	
	public PageDto(UrlDto urlDto){
		this.urlDto = urlDto;
		this.urlsColetadas = new LinkedList<UrlDto>();
		this.listas = new LinkedList<ListDto>();
	}
	
	public PageDto(){
		this.urlDto = null;
		this.listas = null;
		this.doc = null;
		this.urlsColetadas = null;
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

	
	public void addLista(ListDto listDto){
		this.listas.add(listDto);
	}

	public List<ListDto> getListas() {
		return listas;
	}
	
	
	public UrlDto[] getUrlColetadas(){
		return urlsColetadas.toArray(new UrlDto[urlsColetadas.size()]);
	}
	

}
