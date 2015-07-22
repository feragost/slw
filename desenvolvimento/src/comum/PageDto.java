package comum;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Document;

import wrapper.agrup.GrupoDeRegistrosSemelhantes;

public class PageDto {
	
	private UrlDto urlDto;
	private Document doc;
	
	private List<UrlDto> urlsColetadas;
	private List<ListDto> listas;
	
	
	public PageDto(UrlDto urlDto){
		this.urlDto = urlDto;
		this.listas = new LinkedList<ListDto>();
		this.urlsColetadas = new LinkedList<UrlDto>();
	}
	
	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public void addLista(ListDto listDto){
		this.listas.add(listDto);
	}
	
	public void addUrlColetada(UrlDto urlDto){
		this.urlsColetadas.add(urlDto);
	}

	public UrlDto getUrlDto() {
		return urlDto;
	}

	public List<ListDto> getGrupos() {
		return listas;
	}
	
	public UrlDto[] getUrlColetadas(){
		return urlsColetadas.toArray(new UrlDto[urlsColetadas.size()]);
	}
	

}
