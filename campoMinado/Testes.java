package campoMinado;

public class Testes {

	public Testes() {
	}
	
	public void checkNome (String nome) throws Exception {
		if (nome.length() < 3) {
			throw new Exception("O nome deve ter 3 ou mais letras.");
		}
	}
	
	public void checkDimensao (int x, int y) throws Exception {
		if (x < 8 || y < 8) {
			throw new Exception("O tamanho m�nimo do tabuleiro � [8,8]");
		}
	}
	
	public void checkJogada (int xEntrada, int yEntrada, int xMaior, int yMaior, Jogo jogo) throws Exception {
		if (xEntrada >= xMaior || yEntrada >= yMaior) {
			throw new Exception("Jogada inv�lida");
		
		}else if (jogo.getTabuleiro()[xEntrada][yEntrada].getEstadoZona().equals(EstadoZona.REVELADO)) {
			throw new Exception("Zona j� revelada");
		}
	}
	
	public void checkZonaRevelada (int xEntrada, int yEntrada, Jogo jogo) throws Exception {
		if (jogo.getTabuleiro()[xEntrada][yEntrada].getEstadoZona().equals(EstadoZona.REVELADO)) {
			throw new Exception("Zona j� revelada");
		}
	}
	
	
}
