package database;

import java.util.HashMap;
import java.util.LinkedList;

import comum.PageDto;
import comum.UrlDto;

public class EntityAuth {
	
	public static synchronized HashMap<String, Long> insert(PageDto pageDto){
		
		SequenceCtrl seqCtrl = SequenceCtrl.getInstance();
		
		UrlDto[] urlDtos = pageDto.getUrlColetadas();
		
		HashMap<String, Long> hashUrlDtos = new HashMap<String, Long>();
		for(UrlDto urlDto : urlDtos){		
			
			if( ! hashUrlDtos.containsKey(urlDto.getAuth()) ){
				hashUrlDtos.put(urlDto.getAuth(), 0L);
			}
			
		}
		
		String sql = "SELECT id, descricao FROM tb_auth WHERE descricao IN ( ? )";
		String x = "";
		for(String auth : hashUrlDtos.keySet()){			
			x += ",'" + auth + "'";
		}
		x = x.replaceFirst(",", "");
		sql = sql.replace("?", x);
		
		String[][] result = Database.getMatrizOf(sql);
		for(String[] reg : result){
			Long idAuth = Long.parseLong(reg[0]);
			hashUrlDtos.put(reg[1], idAuth);
		}
		
		LinkedList<String> sqls = new LinkedList<String>();
		for(String auth : hashUrlDtos.keySet()){
			
			long idAuth = hashUrlDtos.get(auth);
			if(idAuth == 0L){
				
				long novoIdAuth = seqCtrl.getNextIdAuth();				
				String sqlInsert = "INSERT INTO tb_auth VALUES ( " + novoIdAuth + " , '" + auth + "' )";
				sqls.addLast(sqlInsert);
				hashUrlDtos.put(auth, novoIdAuth);
				
			}
			
		}
		
		Database.insert(sqls);
		
		return hashUrlDtos;
		
	}

}
