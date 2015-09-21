package database;

import java.util.LinkedList;

import crawler.Settings;
import crawler.VisitStatus;

public class Schema {
	


	public static void doit() {

		LinkedList<String> sqls = new LinkedList<String>();		
		
		//sqls.addLast("DROP ALL OBJECTS;");

		sqls.addLast("DROP TABLE IF EXISTS TB_LISTA;");
		sqls.addLast("DROP TABLE IF EXISTS TB_VISIT;");		
		sqls.addLast("DROP TABLE IF EXISTS TB_SETTINGS;");
		sqls.addLast("DROP TABLE IF EXISTS TB_URL;");
		sqls.addLast("DROP TABLE IF EXISTS TB_AUTH;");
		sqls.addLast("DROP TABLE IF EXISTS TB_VISIT_STATUS;");
		
		sqls.addLast("DROP INDEX IF EXISTS index_url_desc");
		sqls.addLast("DROP INDEX IF EXISTS index_auth_desc");
		sqls.addLast("DROP INDEX IF EXISTS index_to_visit");
		sqls.addLast("DROP INDEX IF EXISTS index_visit_status_on_url");
		sqls.addLast("DROP INDEX IF EXISTS index_visit_status_on_visit");
		sqls.addLast("DROP INDEX IF EXISTS index_visit");
		
		/*
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
		*/
		
		//new
		sqls.addLast("CREATE TABLE TB_VISIT_STATUS (ID INT PRIMARY KEY, DESCRICAO VARCHAR(20));");
		sqls.addLast("CREATE TABLE TB_AUTH (ID BIGINT PRIMARY KEY, DESCRICAO VARCHAR(60));");
		sqls.addLast("CREATE TABLE TB_URL (ID BIGINT PRIMARY KEY, ID_AUTH BIGINT, DESCRICAO VARCHAR(4000), ID_VISIT_STATUS INT, ID_WRAPPER_STATUS INT);");
		sqls.addLast("CREATE TABLE TB_SETTINGS (ID BIGINT PRIMARY KEY, NUM_MIN_REGISTROS INT, NUM_MAX_CARACTERES INT, NUM_DERIVACOES INT);");
		sqls.addLast("CREATE TABLE TB_VISIT (ID BIGINT PRIMARY KEY, ID_URL BIGINT, ID_SETTINGS INT);");
		sqls.addLast("CREATE TABLE TB_LISTA (ID BIGINT PRIMARY KEY, ID_VISIT BIGINT, CONTENT TEXT);");
		
		sqls.addLast("ALTER TABLE TB_URL ADD CONSTRAINT TB_URL_TB_AUTH FOREIGN KEY (ID_AUTH) REFERENCES TB_AUTH (ID);");
		sqls.addLast("ALTER TABLE TB_VISIT ADD CONSTRAINT TB_VISIT_TB_URL FOREIGN KEY (ID_URL) REFERENCES TB_URL (ID);");
		sqls.addLast("ALTER TABLE TB_URL ADD CONSTRAINT TB_URL_TB_VISIT_STATUS FOREIGN KEY (ID_VISIT_STATUS) REFERENCES TB_VISIT_STATUS (ID);");
		sqls.addLast("ALTER TABLE TB_URL ADD CONSTRAINT TB_URL_TB_VISIT_STATUS_WRAPPER FOREIGN KEY (ID_WRAPPER_STATUS) REFERENCES TB_VISIT_STATUS (ID);");
		sqls.addLast("ALTER TABLE TB_VISIT ADD CONSTRAINT TB_VISIT_TB_SETTINGS FOREIGN KEY (ID_SETTINGS) REFERENCES TB_SETTINGS (ID);");
		sqls.addLast("ALTER TABLE TB_LISTA ADD CONSTRAINT TB_LISTA_TB_VISIT FOREIGN KEY (ID_VISIT) REFERENCES TB_VISIT (ID);");
		
		sqls.addLast("CREATE INDEX index_auth_desc ON TB_AUTH(DESCRICAO);");
		sqls.addLast("CREATE INDEX index_url_desc ON TB_URL(DESCRICAO);");
		sqls.addLast("CREATE INDEX index_visit_status_on_url ON TB_URL(ID_VISIT_STATUS);");
		sqls.addLast("CREATE INDEX index_to_visit ON TB_URL(ID_WRAPPER_STATUS);");
		sqls.addLast("CREATE INDEX index_visit ON TB_LISTA(ID_VISIT);");
		
		
		
		
		
		VisitStatus[] arrUrlStatus = VisitStatus.values();
		for(VisitStatus urlStatus : arrUrlStatus){
			int id = urlStatus.getId();
			String desc = urlStatus.getDescricao();			
			sqls.addLast("INSERT INTO TB_VISIT_STATUS ( ID , DESCRICAO ) VALUES (" + id + ",'" + desc + "');");			
		}
		
		Settings[] settingsValues = Settings.values();
		for(Settings settings : settingsValues ){
			int id = settings.getId();
			int numMinRegistros = settings.getNumMinRegistros();
			int numMaxCaracteres = settings.getNumMaxCaracteres();
			int numDerivacoes = settings.getNumDeDerivacoes();
			sqls.addLast("INSERT INTO tb_settings VALUES("+id+","+numMinRegistros+","+numMaxCaracteres+","+numDerivacoes+");");
		}
		
		

		Insert.doit(sqls);

	}

	public static void main(String[] args) {
		
		Schema.doit();
		//Schema.delete();

	}

}
