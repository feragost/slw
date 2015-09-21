package crawler;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import comum.PageDto;
import comum.UrlDto;
import database.Database;

public class RunnableSeedsCtrlUrlCrawl extends RunnableSeedsCtrl {

	public RunnableSeedsCtrlUrlCrawl(LinkedBlockingQueue<PageDto> seeds, UrlsRecentementeVisitadas urlsVisited, int qtd) {
		super(seeds, urlsVisited, qtd);
	}

	@Override
	protected List<PageDto> getPageDtos() {
		
		LinkedList<UrlDto> urlDtos = new LinkedList<UrlDto>();
		
		String sql = "SELECT * FROM tb_url WHERE id_visit_status = " + VisitStatus.NOVA_URL.getId() + " ORDER BY RAND() LIMIT 200";
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
