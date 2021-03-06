package wrapper.comum;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import wrapper.WrapperConfig;

public class Registro {

	private String texto;
	private ElementoTexto[] elementosTextos;
	private HashSet<Token> caracteresEspeciais;
	private HashSet<Integer> tiposDeTokens;
	private WrapperConfig wrapperConfig;
	

	public Registro(ElementoTexto et) {
		this.wrapperConfig = WrapperConfig.getInstance();
		this.texto = et.getTexto();
		this.elementosTextos = new ElementoTexto[1];
		this.elementosTextos[0] = et;
		analisarTokens();
	}

	public Registro(ElementoTexto[] ets) {
		this.elementosTextos = ets;
		this.texto = "";
		for (ElementoTexto et : ets) {
			this.texto += et.getTexto() + " ";
		}
		this.texto = this.texto.trim();
		analisarTokens();
	}
	
	private void analisarTokens(){
		
		caracteresEspeciais = new HashSet<Token>();
		tiposDeTokens = new HashSet<Integer>();
		HashSet<Integer> tiposTokenIdent = WrapperConfig.tiposTokenIdent;
				
		
		for(ElementoTexto et : elementosTextos){
						
			for(Token t : et.getTokens()){
				
				if(tiposTokenIdent.contains(t.getTipo())){
					
					tiposDeTokens.add(t.getTipo());
					
					IdentTipoTokenItem identTipo = new IdentTipoTokenItem(t.getTipo());
									
				}else if(t.getTipo() == TipoToken.CARACTERE_ESPECIAL.ordinal()){
					
					caracteresEspeciais.add(t);
					
					IdentSepItem identSep = new IdentSepItem(t);
					
				}
				
			}
			
		}
		
	}
	
	public boolean contemCaractereEspecial(Token token){
		return caracteresEspeciais.contains(token);
	}
	
	public boolean contemTipo(Integer tipo){
		return tiposDeTokens.contains(tipo);
	}
	
	public List<Token> getCaracteresEspeciais(){
		return new ArrayList<Token>(caracteresEspeciais);
	}
	
	public List<Integer> getTiposDeTokenIdent(){
		return new ArrayList<Integer>(tiposDeTokens);
	}

	public String getTexto() {
		return texto;
	}

	public ElementoTexto[] getElementoTexto() {
		return elementosTextos;
	}
	

	public static boolean validarRegistro(Registro registro) {
		
		WrapperConfig wrapperConfig = WrapperConfig.getInstance();
		
		String texto = registro.getTexto();
		int registroLength = texto.length();
		
		if (registroLength > wrapperConfig.getNumeroMaximoDeCaracteresPorRegistro()) {
			return false;
		}
				
		
		int numEspeciais = 0;
		
		for(char c : texto.toCharArray()){
			
			if((int) c > 256 && !Character.isSpace(c)){
				numEspeciais++;
			}			
			
		}
		
		if(numEspeciais * 3 > registroLength){
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
	
	public static void main(String[] args) {
		
		System.out.println((int) 'A');
	}

}
