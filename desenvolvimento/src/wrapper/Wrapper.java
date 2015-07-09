package wrapper;

import org.jsoup.nodes.Document;

import wrapper.armazenamento.Armazenamento;
import wrapper.comum.PageDto;
import wrapper.parser.Parser;

public class Wrapper {
	
	public void extrair(Document doc, PageDto page){
		
		Parser parser = new Parser();
		parser.parse(doc, page);
		
		Armazenamento arm = new Armazenamento();
		arm.armazenar(page);
		
	}
	
	

}
