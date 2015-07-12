package wrapper.comum;

import java.util.LinkedList;
import java.util.List;

import wrapper.agrup.GrupoDeRegistrosSemelhantes;

public class PageDto {
	
	private String url;
	private int id_url;
	
	private List<ListDto> listas;
	
	public PageDto(String url, int id_url){
		this.url = url;
		this.id_url = id_url;
		this.listas = new LinkedList<ListDto>();
	}
	
	public void addLista(ListDto listDto){
		this.listas.add(listDto);
	}

	public String getUrl() {
		return url;
	}

	public int getId_url() {
		return id_url;
	}

	public List<ListDto> getGrupos() {
		return listas;
	}
	
	

}
