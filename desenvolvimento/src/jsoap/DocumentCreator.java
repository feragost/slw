package jsoap;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DocumentCreator {

	public static Document create(File file) {

		try {

			return Jsoup.parse(file, "UTF-8");

		} catch (Throwable e) {
			//e.printStackTrace();
		}

		return null;

	}

	public static Document create(String urlDesc) {
		
		try {
			
			URL url = new URL(urlDesc);
			return Jsoup.parse(url,10000);		
			
		} catch (Throwable e) {
			//e.printStackTrace();			
		}
		
		return null;

	}

}
