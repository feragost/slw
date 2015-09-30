package showresult;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import database.Database;

public class ExportListsToTxt {
	
	public static void main(String[] args) {
		
		String sql = "SELECT content FROM tb_lista";
		String[][] matrix = Database.getMatrizOf(sql);
		
		File dir = new File("txts_lists");
		
		if(dir.exists()){
			try {
				FileUtils.deleteDirectory(dir);
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
		
		dir.mkdir();
		
		for(int i = 0 ; i < matrix.length ; i++){
			
			String content = matrix[i][0];
			
			try {
				String nomeFile = "lista_" + i;
				String pathFile = "txts_lists/" + nomeFile + ".txt";
				FileUtils.writeStringToFile(new File(pathFile), content);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
