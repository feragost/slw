package database;

import java.util.LinkedList;
import java.util.List;

public class Database {
	
	public static synchronized void insert(String sql){
		LinkedList<String> sqls = new LinkedList<String>();
		sqls.add(sql);
		Insert.doit(sqls);
	}
		
	public static synchronized void insert(List<String> sqls){
		Insert.doit(sqls);
	}
	
	public static synchronized List<String> selectListOf(String sql) {
		return Select.selectListOf(sql);
	}
	
	public static synchronized String[][] getMatrizOf(String sql) {
		return Select.getMatrizOf(sql);
	}
	
	public static synchronized String getValor(String sql) {
		return Select.getValor(sql);
	}


}
