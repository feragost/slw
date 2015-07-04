package wrapper.tokenizador;

public class TokenizadorDeRegistros {

	private Tokenizador tokenizador;

	public TokenizadorDeRegistros() {
		tokenizador = new Tokenizador();
		// quanto mais específico, mais para cima tem que ficar
		tokenizador.addPadroes(new PadraoDeSeparacao("[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})", true, false));
		tokenizador.addPadroes(new PadraoDeSeparacao("(\\w)+(-(\\w)+)*", true, false));
		tokenizador.addPadroes(new PadraoDeSeparacao("(\\d)+([.|,](\\d)+)*", true, false));
		tokenizador.addPadroes(new PadraoDeSeparacao("\\s", false, false));
		tokenizador.addPadroes(new PadraoDeSeparacao("\\W", true, true));
		// tokenizador.addPadroes(new PadraoDeSeparacao("(\\s)+\\W|\\W(\\s)+", true));
		// tokenizador.addPadroes(new PadraoDeSeparacao("\\A\\W|\\W\\z", true));
	}

	public Token[] tokenizar(String registro) {

		return null;

	}

	

}
