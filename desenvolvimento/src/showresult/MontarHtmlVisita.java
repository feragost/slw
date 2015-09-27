package showresult;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import comum.IdValorDto;
import comum.ListDto;
import comum.RegistroDto;
import comum.UrlDto;
import comum.ValorQtdDto;
import comum.VisitDto;
import crawler.Settings;

public class MontarHtmlVisita {

	public static void doit(String path, UrlDto urlDto, VisitDto visitDto) {
		
		String html = getHtml(path, urlDto, visitDto);
		
		try {
			String nomeFile = "visit" + visitDto.getId();
			String pathFile = path + "/" + nomeFile + ".html";
			FileUtils.writeStringToFile(new File(pathFile), html);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private static String getHtml(String path, UrlDto urlDto, VisitDto visitDto){
		
		String html = "";
		
		html += "<!DOCTYPE html><html><head><title>Visita</title><link rel=\"stylesheet\" type=\"text/css\" href=\"../result.css\"></head><body>";
		
		html += "<a href=\""+urlDto.getUrl()+"\">"+urlDto.getUrl()+"</a>";
		
		Settings settings = visitDto.getSettings();
		
		html += "<div><b>Número mínimo de registros: </b> "+settings.getNumMinRegistros()+"</div>";
		html += "<div><b>Número máximo de caracteres: </b> "+settings.getNumMaxCaracteres()+"</div>";
		html += "<div><b>Número de derivações: </b> "+settings.getNumDeDerivacoes()+"</div>";
		html += "<div><b>Percentual mínimo para identidade prevalecente: </b> "+settings.getPercentIdentidadePrevalecente()+"</div>";

		for (ListDto listDto : visitDto.getListas()) {
			
			html += "<div class=\"listdto\">";

			
			html += "<div class=\"itens\"><div>itens</div><ul>";
			for (String reg : listDto.registros) {				
				html += "<li>"+reg+"</li>";				
			}
			html += "</ul></div>";

			html += "<div class=\"lista\"><div>separadores</div><ul>";
			for (IdValorDto separador : listDto.separadores) {	
				String id = separador.getId();
				String valor = separador.getValor();
				html += "<li><b>"+id+"</b> "+valor+"</li>";
			}
			html += "</ul></div>";

			html += "<div class=\"lista\"><div>identidades</div><ul>";
			for (ValorQtdDto identidade : listDto.identsDesc) {
				String qtd = identidade.getQtd() + "x";
				String ident = identidade.getValor();
				html += " <li><b>"+qtd+"</b> "+ident+"</li>";
			}
			html += "</ul></div>";

			html += "<div class=\"separados\"><div>registros separados:</div><br/><table>";
			for (RegistroDto regDto : listDto.registroDtos) {
				html += "<tr>";
				for (String att : regDto.getAtributos()) {
					html += "<td>"+att+"</td>";
				}
				html += "</tr>";
			}
			html += "</table></div></div>";
			
			html += "</div>";

		}
		
		html += "</body></html>";
		
		return html;
		
	}

}
