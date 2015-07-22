package wrapper;

import org.jsoup.nodes.Document;

import comum.PageDto;

import wrapper.armazenamento.Armazenamento;
import wrapper.parser.Parser;

public class Wrapper {
	
	public void wrap(PageDto page){
		
		Parser parser = new Parser();
		parser.parse(page);		
		
		/*
		Armazenamento arm = new Armazenamento();
		arm.armazenar(page);
		*/
	}
	
	

}
