package main;

import crawler.VisitStatus;
import database.Database;

public class MainChoose {
	
	public static void main(String[] args) {
		new MainChoose().doit(5);
	}
	
	public MainChoose(){
		
	}
	
	public void doit(int num){
		
		updateToVisit(num);
		
	}
	
	private void updateToVisit(int num){
		
		int id_coletar = VisitStatus.COLETAR_LISTAS.getId();
		int id_nova = VisitStatus.NOVA_URL.getId();
		
		String sql = "UPDATE tb_url SET id_wrapper_status = "+id_coletar+" WHERE id IN(SELECT id FROM tb_url WHERE id_wrapper_status = "+id_nova+" ORDER BY random() LIMIT "+num+")";
		Database.insert(sql);
	}
	

}
