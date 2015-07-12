package wrapper.agrup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import wrapper.comum.Identidade;
import wrapper.comum.Registro;
import wrapper.comum.Token;

public class Extrator {
	
	private int numeroDeDerivacoes;
	private int numeroMinimoDeRegistrosAdjacentes;
	
	public Extrator(int numeroDeDerivacoes, int numeroMinimoDeRegistrosAdjacentes){
		this.numeroDeDerivacoes = numeroDeDerivacoes;
		this.numeroMinimoDeRegistrosAdjacentes = numeroMinimoDeRegistrosAdjacentes;
	}
	
	public List<GrupoDeRegistrosSemelhantes> extrair(List<Registro> registros){
		
		LinkedList<GrupoDeRegistrosSemelhantes> gruposDeRegistrosSemelhantes = new LinkedList<GrupoDeRegistrosSemelhantes>();
		
		if(registros.size() < numeroDeDerivacoes + 1)
			return gruposDeRegistrosSemelhantes;
		
		ArrayList<Nodo> nodosRegistros = new ArrayList<Nodo>();
		Iterator<Registro> itRegistros = registros.iterator();
		for(int i = 0 ; i < registros.size() ; i++)
			nodosRegistros.add(new NodoRegistro(itRegistros.next(),i));
				
		
		for(int i = 0 ; i < numeroDeDerivacoes ; i++){
			nodosRegistros = derivar(nodosRegistros);
		}
		
				
		List<Grupo> grupos = agruparNodosAdjacentesSemelhantes(nodosRegistros);
				
		for(Grupo grupo : grupos){
			
			int tam = grupo.indexFinal - grupo.indexInicial + 1;
			
			if(tam >= numeroMinimoDeRegistrosAdjacentes){
				
				List<Registro> registrosDoGrupo = registros.subList(grupo.indexInicial, grupo.indexFinal + 1);
				GrupoDeRegistrosSemelhantes grupoDeRegistrosSemelhantes = new GrupoDeRegistrosSemelhantes(registrosDoGrupo);
				
				Identidade[] identidades = grupo.getIdentidades();
				for(Identidade identidade : identidades){
					grupoDeRegistrosSemelhantes.addIdentidade(identidade);
				}
				
				gruposDeRegistrosSemelhantes.add(grupoDeRegistrosSemelhantes);
				
			}
			
		}
		
		return gruposDeRegistrosSemelhantes;
		
	}
	
	private ArrayList<Nodo> derivar(List<Nodo> lista){
		
		if(lista.size() < 2)
			throw new RuntimeException("Lista para derivar deve conter no mínimo 2 elementos.");
		
		ArrayList<Nodo> listaDerivada = new ArrayList<Nodo>();
		
		Iterator<Nodo> it = lista.iterator();
		Nodo nodo1 = it.next();
		while(it.hasNext()){
			Nodo nodo2 = it.next();
			listaDerivada.add(new NodoDeriv(nodo1, nodo2));
			nodo1 = nodo2;
		}		
		
		return listaDerivada;
		
	}
	
	
	
	private List<Grupo> agruparNodosAdjacentesSemelhantes(List<Nodo> nodos){
						
		AgruparNodos agrupNodos = new AgruparNodos();
		
		Iterator<Nodo> it = nodos.iterator();
		
		while(it.hasNext())		
			agrupNodos.add(it.next());
		
		return agrupNodos.getGrupos();
	}

}
