package wrapper;


import comum.PageDto;
import comum.VisitDto;
import crawler.Settings;
import wrapper.parser.Parser;

public class Wrapper {
	
	private Settings settings;
	
	public Wrapper(Settings settings){
		WrapperConfig.defineSettings(settings);
		this.settings = settings;
	}
		
	public Settings getSettings(){
		return settings;
	}
	
	public void wrap(PageDto page){
		
		Parser parser = new Parser();
		parser.parse(page);
		
		//combinar listas adjacentes com a mesma identidade
		
				
	}
	
	

}
