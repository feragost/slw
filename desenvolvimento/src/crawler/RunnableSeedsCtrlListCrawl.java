package crawler;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import comum.PageDto;
import comum.UrlDto;
import database.Database;

public class RunnableSeedsCtrlListCrawl extends RunnableSeedsCtrl{

	public RunnableSeedsCtrlListCrawl(LinkedBlockingQueue<PageDto> seeds, UrlsRecentementeVisitadas urlsVisited, int id) {
		super(seeds, urlsVisited, id);
	}

	@Override
	protected List<PageDto> getPageDtos() {
		
		LinkedList<UrlDto> urlDtos = new LinkedList<UrlDto>();
		
		String sql = "SELECT tb_url.id, tb_url.id_auth, tb_url.descricao, tb_visit.id_settings "
						+ "FROM tb_visit INNER JOIN tb_url ON tb_visit.id_url = tb_url.id "
						+ "WHERE tb_visit.id_visit_status = " + VisitStatus.NOVA_URL.getId() 
						+ " ORDER BY RAND() LIMIT 200";
		String[][] matrix = Database.getMatrizOf(sql);
		
		for(String[] reg : matrix){
			
			long id = Long.parseLong(reg[0]);
			long idAuth = Long.parseLong(reg[1]);
			String url = reg[2];
			
			UrlDto urlDto = new UrlDto(url, id, idAuth);
			urlDtos.addLast(urlDto);
			
		}
		
		List<UrlDto> urlDtosFiltrados = urlsVisited.filterUrls(urlDtos);
		System.out.println("urlDtosFiltrados.size(): " + urlDtosFiltrados.size());
		
		List<PageDto> pageDtos = new LinkedList<PageDto>();
		for(UrlDto urlDto : urlDtosFiltrados){
			pageDtos.add(new PageDto(urlDto));
		}
		
		return pageDtos;
	}

}
