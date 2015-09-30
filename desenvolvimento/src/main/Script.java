package main;


public class Script {
	
	public static void main(String[] args) {
		
		Rebuild.doit();
		new MainCrawler().coletar(200);		
		new MainChoose().doit(400);
		new MainWrapper().doit();
		
	}

}
