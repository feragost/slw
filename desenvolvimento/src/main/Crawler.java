package main;

import java.util.LinkedList;
import java.util.List;

import jsoap.DocumentCreator;

import org.jsoup.nodes.Document;

import comum.PageDto;
import comum.UrlDto;
import crawler.RunnableRemoveVisitados;
import crawler.UrlColector;
import crawler.UrlsRecentementeVisitadas;
import crawler.VisitStatus;
import database.Database;
import database.EntityUrl;
import database.UpdateVisitStatus;

public class Crawler {

	private UrlsRecentementeVisitadas urlsVisited;
	private RunnableRemoveVisitados runnableRemoveVisitados;
	private UrlColector c;

	public Crawler() {
		urlsVisited = new UrlsRecentementeVisitadas();
		c = new UrlColector();
		runnableRemoveVisitados = new RunnableRemoveVisitados(urlsVisited);
		new Thread(runnableRemoveVisitados).start();
	}

	public static void main(String[] args) {

		Crawler crawler = new Crawler();
		crawler.coletar(5);

	}

	public void coletar(int numberOfLinksToColect) {

		while (numberOfLinksToColect > 0) {

			List<PageDto> pageDtos = getPageDtos();

			for (PageDto pageDto : pageDtos) {

				numberOfLinksToColect--;
				System.out.println("Visitando a pagina: " + pageDto.getUrlDto().getUrl());

				Document doc = DocumentCreator.create(pageDto.getUrlDto().getUrl());

				try {

					pageDto.setDoc(doc);
					c.coletar(pageDto);
					EntityUrl.inserirUrlColetadas(pageDto);

					UpdateVisitStatus.doit(pageDto.getUrlDto().getId(),	VisitStatus.LINKS_COLETADOS);

				} catch (Throwable t) {

					UpdateVisitStatus.doit(pageDto.getUrlDto().getId(),	VisitStatus.PROBLEMA);

				} finally {

					urlsVisited.urlVisitada(pageDto.getUrlDto());

				}

				if (numberOfLinksToColect <= 0) {
					break;
				}

			}

		}

		runnableRemoveVisitados.stop();

	}

	protected List<PageDto> getPageDtos() {

		LinkedList<UrlDto> urlDtos = new LinkedList<UrlDto>();

		String sql = "SELECT id, id_auth, descricao FROM tb_url WHERE id_visit_status = "
				+ VisitStatus.NOVA_URL.getId() + " ORDER BY random() LIMIT 200";
		String[][] matrix = Database.getMatrizOf(sql);

		for (String[] reg : matrix) {

			long id = Long.parseLong(reg[0]);
			long idAuth = Long.parseLong(reg[1]);
			String url = reg[2];

			UrlDto urlDto = new UrlDto(url, id, idAuth);
			urlDtos.addLast(urlDto);

		}

		List<UrlDto> urlDtosFiltrados = urlsVisited.filterUrls(urlDtos);
		System.out.println("urlDtosFiltrados.size(): "
				+ urlDtosFiltrados.size());

		List<PageDto> pageDtos = new LinkedList<PageDto>();
		for (UrlDto urlDto : urlDtosFiltrados) {
			pageDtos.add(new PageDto(urlDto));
		}
		return pageDtos;

	}

}
