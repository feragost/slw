package wrapper.parser;

import java.util.LinkedList;

public class Path {
	
	private LinkedList<String> pathDesc;
	
	public Path(LinkedList<String> pathDesc){
		this.pathDesc = pathDesc;
	}
	
	public String[] getPathDesc(){
		return pathDesc.toArray(new String[pathDesc.size()]);
	}
	
	

}
