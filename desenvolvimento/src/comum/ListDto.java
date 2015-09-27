package comum;


public class ListDto {
	
	public String[] registros;
	public RegistroDto[] registroDtos;
	
	public IdValorDto[] tiposEspeciais;
	public IdValorDto[] separadores;
	public IdValorDto[] paths;
	
	public ValorQtdDto[] identsDesc;	
	public ValorQtdDto[] pathsDesc;
	
	public ValorQtdDto getIdentComMaiorQuantidade(){
		int qtdMax = 0;
		ValorQtdDto vqtdMax = null;
		for(ValorQtdDto vqtd : identsDesc){
			if(vqtd.getQtd() > qtdMax){
				qtdMax = vqtd.getQtd();
				vqtdMax = vqtd;
			}
		}
		
		return vqtdMax;
	}

}
