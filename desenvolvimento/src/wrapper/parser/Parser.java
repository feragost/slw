package wrapper.parser;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import wrapper.agrup.Agrupador;
import wrapper.comum.ElementoTexto;
import wrapper.comum.PageDto;
import wrapper.comum.Path;
import wrapper.comum.Registro;

public class Parser {
	
	private PathCtrl pathCtrl;
	private TokenizadorElementoTexto tokenizadorET;
	private PageDto page;
	private Agrupador agrupador;

	public void parse(Document doc, PageDto page) {
		
		this.pathCtrl = new PathCtrl();
		this.tokenizadorET = new TokenizadorElementoTexto();
		this.page = page;
		this.agrupador = new Agrupador(page);		
		
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
			
			if(registrosChilds.size() != 0)
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
