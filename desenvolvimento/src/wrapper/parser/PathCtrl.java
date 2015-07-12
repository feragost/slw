package wrapper.parser;

import java.util.Hashtable;
import java.util.LinkedList;

import wrapper.comum.Path;

public class PathCtrl {
	
	private Hashtable<String, Path> hashPath;
	private LinkedList<String> pathAtual;
	private int idCtrl;
	
	public PathCtrl(){
		hashPath = new Hashtable<String, Path>();
		pathAtual = new LinkedList<String>();
		idCtrl = 0;
	}
	
	public void in(String tag){
		pathAtual.addLast(tag);
	}
	
	public void out(){
		pathAtual.removeLast();
	}
	
	public Path getPath(){
		
		LinkedList<String> listPath = new LinkedList<String>();
		String pathDescAtual = "";
		for(String tag : pathAtual){
			pathDescAtual += "." + tag;
			listPath.addLast(tag);
		}
			
		
		if( ! hashPath.containsKey(pathDescAtual)){
			
			Path path = new Path(listPath, ++idCtrl);
			hashPath.put(pathDescAtual, path);			
			
		}
		
		return hashPath.get(pathDescAtual);
		
	}

}
