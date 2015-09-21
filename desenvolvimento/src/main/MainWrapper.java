package main;

import java.util.LinkedList;
import java.util.List;

import jsoap.DocumentCreator;

import org.jsoup.nodes.Document;

import wrapper.Wrapper;
import comum.PageDto;
import comum.UrlDto;
import crawler.Settings;
import crawler.VisitStatus;
import database.Database;
import database.EntityLista;
import database.UpdateVisitStatus;
import database.UpdateWrapperStatus;

public class MainWrapper {
	
	private Wrapper[] ws;
	
	public static void main(String[] args) {
		new MainWrapper().doit();
	}
	
	
	public MainWrapper(){
		
		Settings[] settingsValues = Settings.values();
		ws = new Wrapper[settingsValues.length];
		for(int i = 0 ; i < settingsValues.length ; i++){
			ws[i] = new Wrapper(settingsValues[i]);
		}
		
	}
	
	public void doit(){
		
		List<PageDto> pageDtos = getPageDtos();
		int numVisitas = 0;
		int numVisitasMax = pageDtos.size();
		
		System.out.println("Para coletar : " + numVisitasMax + " paginas.");
		
		for(PageDto pageDto : pageDtos){
			
			numVisitas++;
			System.out.println(numVisitas + "/" + numVisitasMax + " : " + pageDto.getUrlDto().getUrl());
			
			Document doc = DocumentCreator.create(pageDto.getUrlDto().getUrl());
			
			if(doc == null){
				UpdateWrapperStatus.doit(pageDto.getUrlDto().getId(), VisitStatus.PROBLEMA); 
			}
			
			pageDto.setDoc(doc);
			
			for(Wrapper w : ws){
				w.wrap(pageDto);
				EntityLista.inserirListasColetadas(pageDto);
			}
			
			UpdateWrapperStatus.doit(pageDto.getUrlDto().getId(), VisitStatus.LISTAS_COLETADAS); 
			
		}
		
	}
	
	protected List<PageDto> getPageDtos() {
		
		LinkedList<UrlDto> urlDtos = new LinkedList<UrlDto>();
		
		String sql = "SELECT id, id_auth, descricao FROM tb_url WHERE id_wrapper_status = " + VisitStatus.COLETAR_LISTAS.getId();
		String[][] matrix = Database.getMatrizOf(sql);
		
		for(String[] reg : matrix){
			
			long id = Long.parseLong(reg[0]);
			long idAuth = Long.parseLong(reg[1]);
			String url = reg[2];
			
			UrlDto urlDto = new UrlDto(url, id, idAuth);
			urlDtos.addLast(urlDto);
			
		}		
		
		List<PageDto> pageDtos = new LinkedList<PageDto>();
		for(UrlDto urlDto : urlDtos){
			pageDtos.add(new PageDto(urlDto));
		}
		
		return pageDtos;
		
	}

}
