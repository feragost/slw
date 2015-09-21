package crawler;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;


public class RunnableCtrl {
	
	private LinkedList<Map> lista;
	private LinkedList<StopRunnable> runnablesToStop;
	
	public RunnableCtrl(){
		lista = new LinkedList<Map>();
		runnablesToStop = new LinkedList<StopRunnable>();
	}
	
	public void finish(){
		for(Map map : lista){
			for(int i = 0 ; i < map.qtdPoison ; i++){
				try {
					map.queue.put(map.poison);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		for(StopRunnable r : runnablesToStop){
			r.stop();
		}
	}
	
	public void addRecurso(LinkedBlockingQueue<?> queue, Object poison, int qtdPoison){
		Map map = new Map();
		map.queue = queue;
		map.poison = poison;
		map.qtdPoison = qtdPoison;
		lista.addLast(map);
	}
	
	public void addRunnableToStop(StopRunnable r){
		this.runnablesToStop.addLast(r);
	}
	
	public class Map{
		LinkedBlockingQueue queue;
		Object poison;
		int qtdPoison;
	}
	
	
	
	

}
