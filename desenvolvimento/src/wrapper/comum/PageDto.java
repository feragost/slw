package wrapper.comum;

import java.util.LinkedList;
import java.util.List;

import wrapper.agrup.GrupoDeRegistrosSemelhantes;

public class PageDto {
	
	private String url;
	private int id_url;
	
	private List<GrupoDeRegistrosSemelhantes> grupos;
	
	public PageDto(String url, int id_url){
		this.url = url;
		this.id_url = id_url;
		this.grupos = new LinkedList<GrupoDeRegistrosSemelhantes>();
	}
	
	public void addGrupo(GrupoDeRegistrosSemelhantes grupo){
		grupos.add(grupo);
	}

	public String getUrl() {
		return url;
	}

	public int getId_url() {
		return id_url;
	}

	public List<GrupoDeRegistrosSemelhantes> getGrupos() {
		return grupos;
	}
	
	

}
