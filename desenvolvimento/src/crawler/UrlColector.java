package crawler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;

import jsoap.DocumentCreator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import comum.PageDto;
import comum.UrlDto;

public class UrlColector {
	
	public void coletar(PageDto pageDto){
		
		Document doc = pageDto.getDoc();
		
		Elements links = doc.select("a");
		Iterator<Element> it = links.iterator();
		
		
		while(it.hasNext()){
			String stringUrl = it.next().attr("abs:href");
			try {
				URL novaUrl = new URL(stringUrl);
				String protocol = novaUrl.getProtocol();
				String auth = novaUrl.getAuthority();
				
				if( ( protocol.equals("http") || protocol.equals("https") ) && auth.length() < 80 ){
					UrlDto urlDto = new UrlDto(novaUrl.getAuthority(), novaUrl.toString());
					pageDto.addUrlColetada(urlDto);
				}
				
			} catch (MalformedURLException e) {
				
			}			
		}
		
	}
	
	public static void main(String[] args) {
		/*
		UrlColector urlColector = new UrlColector();
		
		UrlDto urlDto = CrawlerConfig.seeds[3];
		Document doc = DocumentCreator.create(urlDto.getUrl());
		PageDto pageDto = new PageDto(urlDto);
		pageDto.setDoc(doc);
		urlColector.coletar(pageDto);
		*/
		
		URL url;
		try {
			url = new URL("http://A Caixa Econômica Federal (CET) aplicou uma nova mudança que deve dificultar o financiamento imobiliário. Após reduzir o valor que pode ser financiado na compra de um bem usado com recursos da poupança, o banco aumentou a tarifa de avaliação de imóvel de R$ 800 para R$ 2.200. O reajuste entrou em vigor na quarta-feira passada (13). Segundo a instituição, os valores estavam “muito defasados e em grande falta de sintonia com o mercado que já estava praticando tarifas na ordem de R$ 2.500”. A tarifa de avaliação é cobrada para avaliar as condições do imóvel e se o preço está compatível com o valor de mercado. O serviço também visa a definir o valor do financiamento. No dia 4 deste mês, a Caixa mudou as regras para financiar um imóvel usado com recursos da poupança. Na prática, o banco reduziu o teto de financiamento, o que, consequentemente, exige um valor de entrada maior para fechar um contrato. Nas operações pelo Sistema Financeiro de Habitação (SFH) – que permite financiar imóveis de até R$ 750 mil em Minas Gerais, Rio de Janeiro, São Paulo e Distrito Federal, e de até R$ 650 mil nos demais Estados -, o financiamento máximo, que era de 80% do valor do imóvel, passou a ser de 50%. Já pelo Sistema Financeiro Imobiliário (SFI) – utilizado na aquisição de imóvel cujo valor ultrapassa R$ 750 mil -, o teto de financiamento deixou de ser de 70% e passou para 40%. Essas mudanças não atingem os financiamentos pelo programa Minha Casa, Minha Vida, e os realizados com recursos do Fundo de Garantia por Tempo de Serviço (FGTS).");
			System.out.println(url.toString());
			System.out.println(url.getProtocol());
			System.out.println(url.getAuthority());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
