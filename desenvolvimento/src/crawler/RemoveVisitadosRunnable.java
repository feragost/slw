package crawler;

public class RemoveVisitadosRunnable implements Runnable {
	
	private UrlsRecentementeVisitadas urlsRecentementeVisitadas;
	
	public RemoveVisitadosRunnable(UrlsRecentementeVisitadas urlsRecentementeVisitadas){
		this.urlsRecentementeVisitadas = urlsRecentementeVisitadas;
	}

	@Override
	public void run() {
		
		long gapRemove = CrawlerConfig.milissegundosSemAcessoAoMesmoAuth;
		long gapWait = gapRemove / 2;
		
		while(true){
			
			try {
				long timeMillisAtual = System.currentTimeMillis();
				Thread.sleep(gapWait);
				urlsRecentementeVisitadas.removeVisited(timeMillisAtual - gapRemove);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	

}
