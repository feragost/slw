package main;

import crawler.InsertSeeds;
import database.Schema;

public class Reconstruir {
	
	public static void main(String[] args) {
		Reconstruir.doit();
	}
	
	public static void doit(){
		
		Schema.doit();
		InsertSeeds.doit();
		new Crawler().coletar(5);
		new Choose().doit(10);
		new MainWrapper().doit();
		
	}

}
