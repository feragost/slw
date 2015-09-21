package crawler;

public class RunnableRemoveVisitados implements Runnable, StopRunnable {
	
	private UrlsRecentementeVisitadas urlsRecentementeVisitadas;
	private boolean run;
	
	public RunnableRemoveVisitados(UrlsRecentementeVisitadas urlsRecentementeVisitadas){
		run = true;
		this.urlsRecentementeVisitadas = urlsRecentementeVisitadas;
	}

	@Override
	public void run() {
		
		long gapRemove = CrawlerConfig.milissegundosSemAcessoAoMesmoAuth;
		long gapWait = gapRemove / 2;
		
		while(run){
			
			try {
				long timeMillisAtual = System.currentTimeMillis();
				Thread.sleep(gapWait);
				urlsRecentementeVisitadas.removeVisited(timeMillisAtual - gapRemove);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	@Override
	public void stop() {
		run = false;		
	}
	

}
