package wrapper.parser;

import java.util.LinkedList;
import java.util.List;

import wrapper.WrapperConfig;

public class Registro {

	private String texto;
	private ElementoTexto[] elementosTextos;

	public Registro(ElementoTexto et) {
		this.texto = et.getTexto();
		this.elementosTextos = new ElementoTexto[1];
		this.elementosTextos[0] = et;
	}

	public Registro(ElementoTexto[] ets) {
		this.elementosTextos = ets;
		this.texto = "";
		for (ElementoTexto et : ets) {
			this.texto += et.getTexto() + " ";
		}
		this.texto = this.texto.trim();
	}

	public String getTexto() {
		return texto;
	}

	public ElementoTexto[] getElementoTexto() {
		return elementosTextos;
	}

	
	public static boolean validarRegistro(Registro registro) {

		if (registro.getTexto().length() > WrapperConfig.numeroMaximoDeCaracteresPorRegistro) {
			return false;
		}

		return true;

	}
	
	public static Registro compilar(List<Registro> registros){
		
		LinkedList<ElementoTexto> elementosTextos = new LinkedList<ElementoTexto>();
		
		for(Registro reg : registros){
			for(ElementoTexto et : reg.getElementoTexto()){
				elementosTextos.addLast(et);
			}
		}
		
		ElementoTexto[] arrElementosTextos = elementosTextos.toArray(new ElementoTexto[elementosTextos.size()]);
		Registro registroCompilado = new Registro(arrElementosTextos);
		
		if(validarRegistro(registroCompilado))		
			return registroCompilado;
		else
			return null;
	}

}
