package main;

import crawler.InsertSeeds;
import database.Schema;

public class Rebuild {
	
	public static void main(String[] args) {
		Rebuild.doit();
	}
	
	public static void doit(){
		
		Schema.doit();
		InsertSeeds.doit();
				
	}

}
