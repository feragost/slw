package showresult;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import comum.UrlDto;
import comum.VisitDto;

public class ShowResult {
	
	
	public static void putInResults(UrlDto urlDto){
		
		VisitDto[] visitDtos = MontarVisitas.doit(urlDto);
		doit(urlDto, visitDtos, "results");
		
	}
	
	public static void putInRevocResults(UrlDto urlDto, VisitDto[] visitDtos){
		
		doit(urlDto, visitDtos, "revoc_results");
		
	}
	
	private static void doit(UrlDto urlDto, VisitDto[] visitDtos, String path){
		
		boolean construir = false;
		for(VisitDto visitDto : visitDtos){
			if(visitDto.getListas().size() > 0){
				construir = true;
				break;
			}
		}
		
		if(!construir){
			return;
		}
				
		File resultsDir = new File(path);
		if(!resultsDir.exists()){
			resultsDir.mkdir();
		}
		
		long idUrl = urlDto.getId();
		String pathResultDir = path + "/url" + idUrl;
		File resultDir = new File(pathResultDir);
		
		if(resultDir.exists()){
			try {
				FileUtils.deleteDirectory(resultDir);
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
		
		resultDir.mkdir();
		
		for(VisitDto visitDto : visitDtos){
			MontarHtmlVisita.doit(pathResultDir, urlDto, visitDto);				
		}
		
		
	}
	
	

}
