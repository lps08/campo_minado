package campoMinado;

public class Testes {

	public Testes() {
	}
	
	/**
	 * Funcao que checa se o nome possui 3 ou mais.
	 * @param nome
	 * @throws Exception
	 */
	public void checkNome (String nome) throws Exception {
		if (nome.length() < 3) {
			throw new Exception("O nome deve ter 3 ou mais letras.");
		}
	}
	
	public void checkDimensao (int x, int y) throws Exception {
		if (x < 8 || y < 8) {
			throw new Exception("O tamanho minimo do tabuleiro e [8,8]");
		}
	}
	
	public void checkJogada (int xEntrada, int yEntrada, int xMaior, int yMaior, Jogo jogo) throws Exception {
		if (xEntrada >= xMaior || yEntrada >= yMaior) {
			throw new Exception("Jogada invalida");
		
		}else if (jogo.getTabuleiro()[xEntrada][yEntrada].getEstadoZona().equals(EstadoZona.REVELADO)) {
			throw new Exception("Zona ja revelada");
		}
	}
	
	public void checkZonaRevelada (int xEntrada, int yEntrada, Jogo jogo) throws Exception {
		if (jogo.getTabuleiro()[xEntrada][yEntrada].getEstadoZona().equals(EstadoZona.REVELADO)) {
			throw new Exception("Zona ja revelada");
		}
	}
}
