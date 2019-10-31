package campoMinado;

public class Main {

	public static void main(String[] args) {
		Tabuleiro tabuleiro = new Tabuleiro(8, 8);
		
		tabuleiro.construirTabuleiro();
		tabuleiro.mostraTabuleiro();
		tabuleiro.zonaPerigoBomba(new Coordenada(8, 4));
	}

}
