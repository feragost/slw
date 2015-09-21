package crawler;

import comum.UrlDto;

public class CrawlerConfig {

	public static final int quantidadeDeWrappers = 4;
	public static final int quantidadeDeDownloadsSimultaneos = 8;
	public static final int milissegundosSemAcessoAoMesmoAuth = 10000;
	public static final int tamanhoBufferSeed = 100;
	public static final int tamanhoBufferDoc = 20;

	public static final UrlDto[] seeds = {
		
			
			new UrlDto("https://pt.wikipedia.org","https://pt.wikipedia.org/wiki/Papa_Francisco"),
			new UrlDto("https://pt.wikipedia.org","https://pt.wikipedia.org/wiki/Suprema_Corte_dos_Estados_Unidos"),
			new UrlDto("https://pt.wikipedia.org","https://pt.wikipedia.org/wiki/Henrique_VIII_de_Inglaterra"),
			new UrlDto("https://pt.wikipedia.org","https://pt.wikipedia.org/wiki/Argentina"),
			new UrlDto("https://pt.wikipedia.org","https://pt.wikipedia.org/wiki/Revolu%C3%A7%C3%A3o_Constitucionalista_de_1932"),
			new UrlDto("https://pt.wikipedia.org","https://pt.wikipedia.org/wiki/Filipe_V_de_Espanha"),
			new UrlDto("https://pt.wikipedia.org","https://pt.wikipedia.org/wiki/Amedeo_Avogadro"),
			new UrlDto("https://pt.wikipedia.org","https://pt.wikipedia.org/wiki/Vinicius_de_Moraes"),
			new UrlDto("https://pt.wikipedia.org","https://pt.wikipedia.org/wiki/Carlos_Chagas"),
			new UrlDto("https://pt.wikipedia.org","https://pt.wikipedia.org/wiki/Nikola_Tesla"),
			new UrlDto("https://pt.wikipedia.org","https://pt.wikipedia.org/wiki/Edward_Heath"),
			new UrlDto("https://pt.wikipedia.org","https://pt.wikipedia.org/wiki/Alec_Baldwin"),
			new UrlDto("https://pt.wikipedia.org","https://pt.wikipedia.org/wiki/Castelo_de_Windsor"),
			new UrlDto("https://pt.wikipedia.org","https://pt.wikipedia.org/wiki/Doctor_Who"),
			new UrlDto("https://pt.wikipedia.org","https://pt.wikipedia.org/wiki/Eol%C3%ADpila"),
			new UrlDto("https://pt.wikipedia.org","https://pt.wikipedia.org/wiki/AK-47"),

	};

}
