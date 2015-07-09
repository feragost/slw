package main;

import java.io.File;

import jsoap.DocumentCreator;

import org.jsoup.nodes.Document;

import wrapper.Wrapper;
import wrapper.comum.PageDto;

public class Main {
	
	public static void main(String[] args) {
		
		Wrapper wrapper = new Wrapper();
		File file = new File("C:/Users/Fernando/Desktop/ex.html"); 
		Document doc = DocumentCreator.create(file);
		PageDto page = new PageDto("ex.html",0);
		wrapper.extrair(doc, page);
		
	}

}
