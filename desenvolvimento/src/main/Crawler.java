package main;

public class Crawler {
	
	public static void main(String[] args) {
		
		if(args.length != 1){
			throw new RuntimeException("\n  crawler necessita apenas de 1 parâmetro");
		}
		
		int numberOfLinksToColect = Integer.parseInt(args[0]);
		
		MainCrawler crawler = new MainCrawler();
		crawler.coletar(numberOfLinksToColect);		
		
	}
	

}
