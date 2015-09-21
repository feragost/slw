package main;

public class MainChoose {
	
	public static void main(String[] args) {
		
		if(args.length != 1){
			throw new RuntimeException("\n  choose necessita apenas de 1 parâmetro");
		}
		
		int numberOfLinksToVisit = Integer.parseInt(args[1]);
		
		new Choose().doit(numberOfLinksToVisit);
		
		
	}

}
