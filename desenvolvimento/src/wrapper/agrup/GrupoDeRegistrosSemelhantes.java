package wrapper.agrup;

import java.util.LinkedList;
import java.util.List;

import wrapper.parser.Registro;
import wrapper.tokenizador.Token;

public class GrupoDeRegistrosSemelhantes {
	
	private List<Registro> registros;
	private List<Identidade> identidades;
	
	public GrupoDeRegistrosSemelhantes(List<Registro> registros){
		this.registros = registros;
		identidades = new LinkedList<Identidade>();
	}
	
	public void addIdentidade(Identidade identidade){
		identidades.add(identidade);
	}
	
	public List<Registro> getRegistros() {
		return registros;
	}
	
	public List<Identidade> getIdentidades(){
		return identidades;
	}

	

}
