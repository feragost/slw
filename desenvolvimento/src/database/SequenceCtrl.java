package database;

public class SequenceCtrl {
	
	private long id_url;
	private long id_auth;
	private long id_lista;
	
	private static final String sqlMaxIdUrl = "SELECT max(id) FROM tb_url;";
	private static final String sqlMaxIdAuth = "SELECT max(id) FROM tb_auth;";
	private static final String sqlMaxIdLista = "SELECT max(id) FROM tb_lista;";
	
	private static SequenceCtrl sequenceCtrl;
	
	private SequenceCtrl(){
		
		String smaxidUrl = Database.getValor(sqlMaxIdUrl);
		String smaxidAuth = Database.getValor(sqlMaxIdAuth);
		String smaxidLista = Database.getValor(sqlMaxIdLista);				
		
		this.id_url = smaxidUrl == null ? 0 : Long.parseLong(smaxidUrl);
		this.id_auth = smaxidAuth == null ? 0 : Long.parseLong(smaxidAuth);
		this.id_lista = smaxidLista == null ? 0 : Long.parseLong(smaxidLista);
		
	}
	
	public static SequenceCtrl getInstance(){
		
		if(sequenceCtrl == null){
			sequenceCtrl = new SequenceCtrl();
		}
		
		return sequenceCtrl;
		
	}
	
	public synchronized long getNextIdUrl(){
		return ++id_url;
	}
	
	public synchronized long getNextIdAuth(){
		return ++id_auth;
	}
	
	public synchronized long getNextIdLista(){
		return ++id_lista;
	}
	
	
	public static void main(String[] args) {
		
		SequenceCtrl seqCtrl = SequenceCtrl.getInstance();
		System.out.println(seqCtrl.getNextIdUrl());
		
	}
	

}
