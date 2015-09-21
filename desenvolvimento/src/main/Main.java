package main;

import java.io.File;

import jsoap.DocumentCreator;

import org.jsoup.nodes.Document;

import comum.PageDto;
import comum.UrlDto;
import wrapper.Wrapper;

public class Main {
	
	public static void main(String[] args) {
		
		//Wrapper wrapper = new Wrapper();
		File file = new File("C:/Users/Fernando/Desktop/ex.html"); 
		//Document doc = DocumentCreator.create(file);
		Document doc = DocumentCreator.create("https://pt.wikipedia.org/wiki/Nikola_Tesla");
		UrlDto urlDto = new UrlDto("localhost", "ex.html", 0);
		PageDto page = new PageDto(urlDto);
		page.setDoc(doc);
		//wrapper.wrap(page);
		
	}

}
