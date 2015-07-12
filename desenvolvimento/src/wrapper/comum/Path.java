package wrapper.comum;

import java.util.LinkedList;

public class Path {
	
	private LinkedList<String> pathDesc;
	private int id;
	
	public Path(LinkedList<String> pathDesc, int id){
		this.pathDesc = pathDesc;
		this.id = id;
	}
	
	public String[] getPathDesc(){
		return pathDesc.toArray(new String[pathDesc.size()]);
	}
	
	public int getId(){
		return this.id;
	}
	
	

}
