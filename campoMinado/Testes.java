package campoMinado;

/**
 * Classe responsável por testar as jogadas pelo jogador.
 * @author lps
 *
 */
public class Testes {

	public Testes() {
	}
	
	/**
	 * Funcao que checa se o nome possui 3 ou mais.
	 * @param nome
	 * @throws Exception
	 */
	public Boolean checkNome (String nome) throws MinhasException {
		if (!nome.matches("([A-Z]{1}[a-z]{2,}\\s*)+")) {
			throw new MinhasException("Nome inválido, deve ser maior ou igual a 3 com a primeira letra maiuscula");
		
		}else return true;
	}
	
	/**
	 * Checa se caso as dimensoes joganda condizem com as dimensoes minima do tabuleiro.	
	 * @param x posicao no eixo x.
	 * @param y posicao no eixo y.
	 * @throws Exception - caso for menor q a permitida, lança o exception.
	 */
	public Boolean checkDimensao (int x, int y) throws MinhasException {
		if (x < 8 || y < 8) {
			throw new MinhasException("O tamanho minimo do tabuleiro e [8,8]");
		
		}else return true;
	}
	
	/**
	 * A cada rodada do usuario, checa se as posicoes passadas ficam no range do tabuleiro.
	 * @param xEntrada - A posicao x jogada pelo jogador.
	 * @param yEntrada - A posicao y jogada pelo jogador.
	 * @param xMaior - O tamnha do tabuleiro no eixo x.
	 * @param yMaior - O tamnha do tabuleiro no eixo y.
	 * @param jogo - Onde sera obtido informacoes do jogo como o tabuleiro, por exemplo.
	 * @throws Exception
	 */
	public Boolean checkJogada (int xEntrada, int yEntrada, int xMaior, int yMaior, Jogo jogo) throws MinhasException {
		if (xEntrada >= xMaior || yEntrada >= yMaior) {
			throw new MinhasException("Jogada invalida");
		
		}else if (jogo.getTabuleiro()[xEntrada][yEntrada].getEstadoZona().equals(EstadoZona.REVELADO)) {
			throw new MinhasException("Zona ja revelada");
		
		}else return true;
	}
	
	/**
	 * Checa se a zona jogada pelo jogador, já foi jogada ou não.
	 * @param xEntrada - A posicao x jogada pelo jogador.
	 * @param yEntrada - A posicao y jogada pelo jogador.
	 * @param jogo - Onde sera obtido informacoes do jogo como o tabuleiro, por exemplo.
	 * @throws Exception - Lanca o exception caso as posicoes jogada ja foram reveladas.
	 */
	public Boolean checkZonaRevelada (int xEntrada, int yEntrada, Jogo jogo) throws MinhasException {
		if (jogo.getTabuleiro()[xEntrada][yEntrada].getEstadoZona().equals(EstadoZona.REVELADO)) {
			throw new MinhasException("Zona ja revelada");
		
		}else return true;
	}
	
	/**
	 * Checa se o tipo de jogada informada pelo usuario condiz com as opcoes validas.
	 * @param opcao - int com o numero da opcao desejada pelo usuario
	 * @return - true caso a opcao for valida || false - caso nao passe no teste
	 * @throws MinhasException - Lanca a exception caso nao passe no teste
	 */
	public Boolean checkTipoJogada (int opcao) throws MinhasException{
		if (opcao >= 1 && opcao <=3) {
			return true;
		
		}else throw new MinhasException("Opcao invalida!");
	}
}
