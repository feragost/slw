package main;

public class MainCrawler {
	
	public static void main(String[] args) {
		
		if(args.length != 1){
			throw new RuntimeException("\n  crawler necessita apenas de 1 parâmetro");
		}
		
		int numberOfLinksToColect = Integer.parseInt(args[1]);
		
		Crawler crawler = new Crawler();
		crawler.coletar(numberOfLinksToColect);		
		
	}
	

}
