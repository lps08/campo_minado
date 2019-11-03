package campoMinado;

public class Main {

	public static void main(String[] args) {
		Jogo jogo = new Jogo(8, 8);
		
		jogo.construirTabuleiro();
		jogo.jogada(TipoJogada.revelarZona, new Coordenada(5, 5));
		
		System.out.println("---------------------");
		jogo.mostraTabuleiro2();
		System.out.println("---------------------");
		jogo.mostraTabuleiro();
	}

}
