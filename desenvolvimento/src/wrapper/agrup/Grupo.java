package wrapper.agrup;

import java.util.Hashtable;

import wrapper.comum.Identidade;

public class Grupo {
	
	int indexInicial;
	int indexFinal;
	
	private Hashtable<String, Identidade> identidades;
		
	
	public Grupo(int indexInicial, int indexFinal){
		this.indexInicial = indexInicial;
		this.indexFinal = indexFinal;
		this.identidades = new Hashtable<String, Identidade>();
	}
	
	public void setIdentidade(Identidade identidade){
		String key = identidade.getKey();
		if( ! identidades.containsKey(key)){
			identidades.put(key, identidade);
		}
	}
	
	public Identidade[] getIdentidades(){
		return identidades.values().toArray(new Identidade[identidades.size()]);
	}
	
	
	

}
