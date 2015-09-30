package main;

import showresult.ShowResult;
import comum.UrlDto;
import database.Database;

public class MainShowResult {
	
	public static void main(String[] args) {
		
		MainShowResult.doit(200);
		
	}
	
	public static void doit(int qtd){
		
		UrlDto[] urlDtos = getUltimasUrlsVisitadas(qtd);
		for(UrlDto urlDto : urlDtos){
			ShowResult.putInResults(urlDto);
		}
		
	}
	
	public static UrlDto[] getUltimasUrlsVisitadas(int qtd){
		
		String sql = "select * from tb_url where id IN (select id_url from tb_visit group by id_url order by max(id) DESC LIMIT "+qtd+")";
		
		String[][] matrix = Database.getMatrizOf(sql);
		
		UrlDto[] urlDtos = new UrlDto[matrix.length];
		for(int i = 0 ; i < matrix.length ; i++){
			String desc = matrix[i][2];
			long id = Long.parseLong(matrix[i][0]);
			long id_auth = Long.parseLong(matrix[i][1]);
			UrlDto urlDto = new UrlDto(desc, id, id_auth);
			urlDtos[i] = urlDto;
		}
		
		return urlDtos;
		
	}

}
