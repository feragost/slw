package wrapper;

import org.jsoup.nodes.Document;

import comum.PageDto;
import crawler.Settings;
import wrapper.armazenamento.Armazenamento;
import wrapper.parser.Parser;

public class Wrapper {
	
	public Wrapper(Settings settings){
		WrapperConfig.defineSettings(settings);
	}
	
	public void wrap(PageDto page){
		
		Parser parser = new Parser();
		parser.parse(page);		
				
	}
	
	

}
