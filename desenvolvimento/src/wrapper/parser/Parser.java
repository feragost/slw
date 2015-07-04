package wrapper.parser;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import wrapper.agrup.Agrupador;

public class Parser {
	
	private PathCtrl pathCtrl;
	private TokenizadorElementoTexto tokenizadorET;

	public void parse(Document doc) {
		
		pathCtrl = new PathCtrl();
		tokenizadorET = new TokenizadorElementoTexto();
		
		Element body = doc.getElementsByTag("body").first();
		parse(body);

	}

	private Registro parse(Node node){
		
		String tag = ((Element)node.parent()).tagName();
		pathCtrl.in(tag);				
		
		Registro registro = null;
				
		if(node instanceof Element){
						
			List<Node> childsNode = node.childNodes();
			LinkedList<Registro> registrosChilds = new LinkedList<Registro>();
			
			for(Node child : childsNode){
				
				Registro registroChild = parse(child);
				if(registroChild != null){
					registrosChilds.addLast(registroChild);
				}
				
			}
			
			Agrupador agrupador = new Agrupador();
			registro = agrupador.agrupar(registrosChilds);
						
			
		}else if(node instanceof TextNode){
						
			String texto = ((TextNode) node).text().trim();
			if(texto != null && texto.length() > 0){
				
				Path path = pathCtrl.getPath();
				ElementoTexto novoET = new ElementoTexto(texto, path, tokenizadorET);
				registro = new Registro(novoET);
				
			}
			
			
		}
		
		pathCtrl.out();
		
		return registro;
				
	}
}
