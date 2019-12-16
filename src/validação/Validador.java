package validação;

import acao.Partida;
import excessões.DimensaoTabuleiroException;
import excessões.JogadaException;
import excessões.NomeJogadorException;
import excessões.RespostaFimException;
import excessões.TipoJogadaExcetion;
import excessões.ZonaReveladaException;
import tabuleiro.EstadoZona;

/**
 * Classe responsável por testar as jogadas pelo jogador.
 * @author lps
 *
 */
public class Validador {

	public Validador() {
	}
	
	/**
	 * Funcao que checa se o nome possui 3 ou mais.
	 * @param nome - Nome a ser validado.
	 * @throws Exception - exceção caso o nome não seja validado.
	 */
	public static Boolean checkNome (String nome) throws NomeJogadorException {
		if (!nome.matches("([A-Z]{1}[a-z]{2,}\\s*)+")) {
			throw new NomeJogadorException("Nome inválido, deve ser maior ou igual a 3 com a primeira letra maiuscula");
		
		}else return true;
	}
	
	/**
	 * Checa se caso as dimensoes joganda condizem com as dimensoes minima do tabuleiro.	
	 * @param x posicao no eixo x.
	 * @param y posicao no eixo y.
	 * @throws Exception - caso for menor q a permitida, lança o exception.
	 */
	public static Boolean checkDimensao (int x, int y) throws DimensaoTabuleiroException {
		if (x < 4 || y < 4) {
			throw new DimensaoTabuleiroException("O tamanho minimo do tabuleiro e [4,4]");
		
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
	public static Boolean checkJogada (int xEntrada, int yEntrada, int xMaior, int yMaior, Partida jogo) throws JogadaException {
		if (xEntrada >= xMaior || yEntrada >= yMaior) {
			throw new JogadaException("Jogada invalida");
		/*
		}else if (jogo.getTabuleiro()[xEntrada][yEntrada].getEstadoZona().equals(EstadoZona.REVELADO)) {
			throw new JogadaException("Zona ja revelada");*/
		
		}else return true;
	}
	
	/**
	 * Checa se a zona jogada pelo jogador, já foi jogada ou não.
	 * @param xEntrada - A posicao x jogada pelo jogador.
	 * @param yEntrada - A posicao y jogada pelo jogador.
	 * @param jogo - Onde sera obtido informacoes do jogo como o tabuleiro, por exemplo.
	 * @throws Exception - Lanca o exception caso as posicoes jogada ja foram reveladas.
	 */
	public static Boolean checkZonaRevelada (int xEntrada, int yEntrada, Partida jogo) throws ZonaReveladaException {
		if (jogo.getTabuleiro().getCampo()[xEntrada][yEntrada].getEstadoZona().equals(EstadoZona.REVELADO)) {
			throw new ZonaReveladaException("Zona ja revelada");
		
		}else return true;
	}
	
	/**
	 * Checa se o tipo de jogada informada pelo usuario condiz com as opcoes validas.
	 * @param opcao - int com o numero da opcao desejada pelo usuario.
	 * @return - true caso a opcao for valida || false - caso nao passe no teste.
	 * @throws MinhasException - Lanca a exception caso nao passe no teste.
	 */
	public static Boolean checkTipoJogada (int opcao) throws TipoJogadaExcetion{
		if (opcao >= 1 && opcao <=3) {
			return true;
		
		}else throw new TipoJogadaExcetion("Opcao invalida!");
	}
	
	/**
	 * Checa se a resposta dada no final do jogo é válida ou não. Sendo que o permitido é 1 e 2.
	 * @param resposta - inteiro contendo a resposta.
	 * @return - retorna um inteiro contendo a resposta dada pelo usuario validada.
	 * @throws RespostaFimException - Lança a exception caso a respota não for válida.
	 */
	public static Boolean checkRespostaFim(int resposta) throws RespostaFimException {
		if (resposta >= 1 && resposta <= 2) {
			return true;
		
		}else throw new RespostaFimException("Resposta inválida");
	}
}
