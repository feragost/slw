package database;

import crawler.UrlStatus;

public class CreateSql {
	
	public static String getSqlUpdateUrlStatus(long id_url, UrlStatus urlStatus){
		String sql = "UPDATE tb_url SET id_url_status = " + urlStatus.getId() + " WHERE id = " + id_url;
		return sql;
	}

}
