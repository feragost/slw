package wrapper.armazenamento;

import java.util.List;

import comum.IdValorDto;
import comum.ListDto;
import comum.PageDto;
import comum.ValorQtdDto;
import wrapper.agrup.GrupoDeRegistrosSemelhantes;
import wrapper.comum.Identidade;
import wrapper.comum.Registro;
import wrapper.comum.TipoToken;
import wrapper.comum.Token;

public class Armazenamento {
	
	public Armazenamento(){
		
	}
	
	public void armazenar(PageDto page){
		
		TipoToken[] valoresTipoToken = TipoToken.values();
		
		//System.out.println("url: " + page.getUrl());
				
		/*
		for(ListDto list : page.getListas()){
			
			System.out.println("----------registros-----------");
			for(String reg : list.registros)
				System.out.println(reg);
			
			System.out.println("----------propriedades--------");
			
			for(IdValorDto idv : list.paths)
				System.out.println("path : " + idv.getId() + " : " + idv.getValor());
			
			for(IdValorDto idv : list.separadores)
				System.out.println("sep : " + idv.getId() + " : " + idv.getValor());
			
			for(IdValorDto idv : list.tiposEspeciais)
				System.out.println("tipo : " + idv.getId() + " : " + idv.getValor());
			
			System.out.println("----------idents--------------");
			
			for(ValorQtdDto vqtd : list.identsDesc)
				System.out.println(vqtd.getQtd() + " : " + vqtd.getValor());
			
			System.out.println("----------identsPath----------");
			
			for(ValorQtdDto vqtd : list.pathsDesc)
				System.out.println(vqtd.getQtd() + " : " + vqtd.getValor());
			
			
			System.out.println("");
			System.out.println("");
			
			
		}*/
		
	}

}
