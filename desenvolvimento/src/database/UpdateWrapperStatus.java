package database;

import java.util.LinkedList;
import java.util.List;

import crawler.VisitStatus;

public class UpdateWrapperStatus {
	
	public static void doit(long id_url, VisitStatus visitlStatus){
		
		List<String> sqls = new LinkedList<String>();
		sqls.add("UPDATE tb_url SET id_wrapper_status = " + visitlStatus.getId() + " WHERE id = " + id_url);
		
		Database.insert(sqls);
		
	}

}
