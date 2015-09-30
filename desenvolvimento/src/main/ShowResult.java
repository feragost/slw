package main;

public class ShowResult {
	
	public static void main(String[] args) {
		
		if(args.length != 1){
			throw new RuntimeException("\n  crawler necessita apenas de 1 parâmetro");
		}
		
		int numberOfLastVisit = Integer.parseInt(args[0]);
		
		MainShowResult.doit(numberOfLastVisit);
		
	}

}
