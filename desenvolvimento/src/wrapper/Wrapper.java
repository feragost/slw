package wrapper;

import org.jsoup.nodes.Document;

import wrapper.parser.Parser;

public class Wrapper {
	
	public void extrair(Document doc){
		
		Parser parser = new Parser();
		parser.parse(doc);
		
	}
	
	

}
