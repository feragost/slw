package database;

import java.util.LinkedList;

import crawler.UrlStatus;

public class Schema {

	public static void doit() {

		LinkedList<String> sqls = new LinkedList<String>();

		sqls.addLast("DROP TABLE IF EXISTS TB_AUTH;");
		sqls.addLast("DROP TABLE IF EXISTS TB_URL_STATUS;");
		sqls.addLast("DROP TABLE IF EXISTS TB_URL;");
		sqls.addLast("DROP TABLE IF EXISTS TB_LISTA;");
		
		sqls.addLast("DROP INDEX IF EXISTS index_url_desc");
		sqls.addLast("DROP INDEX IF EXISTS index_auth_desc");
		sqls.addLast("DROP INDEX IF EXISTS index_url_status");
		
		sqls.addLast("CREATE TABLE TB_AUTH (ID BIGINT PRIMARY KEY, DESCRICAO VARCHAR(60));");
		sqls.addLast("CREATE TABLE TB_URL_STATUS (ID INT PRIMARY KEY, DESCRICAO VARCHAR(20));");
		sqls.addLast("CREATE TABLE TB_URL (ID BIGINT PRIMARY KEY, ID_AUTH BIGINT, DESCRICAO VARCHAR(4000), ID_URL_STATUS INT);");		
		sqls.addLast("CREATE TABLE TB_LISTA (ID BIGINT PRIMARY KEY, ID_URL BIGINT, XML_CONTENT CLOB);");
		
		sqls.addLast("ALTER TABLE TB_URL ADD CONSTRAINT TB_URL_TB_AUTH FOREIGN KEY (ID_AUTH) REFERENCES TB_AUTH (ID);");
		sqls.addLast("ALTER TABLE TB_URL ADD CONSTRAINT TB_URL_TB_URL_STATUS FOREIGN KEY (ID_URL_STATUS) REFERENCES TB_URL_STATUS (ID);");
		sqls.addLast("ALTER TABLE TB_LISTA ADD CONSTRAINT TB_LISTA_TB_URL FOREIGN KEY (ID_URL) REFERENCES TB_URL (ID);");
		
		sqls.addLast("CREATE INDEX index_url_desc ON TB_URL(DESCRICAO);");
		sqls.addLast("CREATE INDEX index_auth_desc ON TB_AUTH(DESCRICAO);");
		sqls.addLast("CREATE INDEX index_url_status ON TB_URL(ID_URL_STATUS);");
		
		
		
		UrlStatus[] arrUrlStatus = UrlStatus.values();
		for(UrlStatus urlStatus : arrUrlStatus){
			int id = urlStatus.getId();
			String desc = urlStatus.getDescricao();			
			sqls.addLast("INSERT INTO TB_URL_STATUS ( ID , DESCRICAO ) VALUES (" + id + ",'" + desc + "');");			
		}
		
		

		Insert.doit(sqls);

	}

	public static void main(String[] args) {
		
		Schema.doit();

	}

}
