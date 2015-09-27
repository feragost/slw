package comum;

import java.util.LinkedList;
import java.util.List;

import crawler.Settings;

public class VisitDto {
	
	private long id;
	private Settings settings;
	private List<ListDto> listas;
	
	public VisitDto(Settings settings){
		this.listas = new LinkedList<ListDto>();		
		this.settings = settings;
	}
	
	public VisitDto(long id, Settings settings){
		this.listas = new LinkedList<ListDto>();
		this.id = id;
		this.settings = settings;
	}
	
	public Settings getSettings(){
		return this.settings;
	}
	
	public void addLista(ListDto listDto){
		this.listas.add(listDto);
	}

	public List<ListDto> getListas() {
		return listas;
	}
	
	public long getId(){
		return this.id;
	}
	
	

}
