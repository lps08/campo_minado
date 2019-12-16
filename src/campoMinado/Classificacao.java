package campoMinado;

import java.util.ArrayList;
import java.util.Comparator;

import acao.StatusPartida;
import tabuleiro.Jogador;

/**
 * Classe responsavel por armazenar as classificacões dos jogadores.
 * @author lps
 */
public class Classificacao {
	private ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
	private static Classificacao singleInstace = null;
	
	/**
	 * Contrutor vazio que irá ser passado nulo ao jogador. Posteriormente, precisará settar o nome do jogador.
	 */
	private Classificacao() {
	}
	
//	/**
//	 * Construtor que irá pegar a instancia do jogador e adicioná-lo ao array de classificação caso seja ganhador.
//	 * @param jogador
//	 */
//	private Classificacao (Jogador jogador) {
//		this.addJogadore(jogador);
//	}
	
//	/**
//	 * Método que retorna uma instancia dessa classe. Caso não esteja criada, ele cria uma instancia e a retorna.
//	 * @param jogador - Parâmetro necessário para o contrutor da classe, em que o mesmo vai ser adicionado ao ranking.
//	 * @return - Instancia da Classe Classificacao.
//	 */
//	public static Classificacao getInstace(Jogador jogador) {
//		if (singleInstace == null) {
//			synchronized (Classificacao.class) {
//				singleInstace = new Classificacao(jogador);			
//			}
//		}
//		return singleInstace;
//	}
	
	/**
	 * Método que retorna uma instancia dessa classe. Caso não esteja criada, ele cria uma instancia e a retorna.
	 * @return - Instancia da Classe Classificacao.
	 */
	public static Classificacao getInstace() {
		if (singleInstace == null) {
			synchronized (Classificacao.class) {
				singleInstace = new Classificacao();			
			}
		}
		return singleInstace;
	}
	
	/**
	 * Metodo que adiciona um jogador para um ArrayList, que armazenara os jogadores vencedores 
	 * @param jogador - Jogador a ser adicionado
	 */
	public void addJogador (Jogador jogador) {
		if (jogador.getStatus().equals(StatusPartida.GANHOU))
			jogadores.add(jogador);
	}
	
	/**
	 * Retorna uma lista com os jogadores que ganharam a partida, ordenados pelo tempo de jogo.
	 */
	@Override
	public String toString () {
		String jogadoresClassificados = "";
		jogadores.sort(new Comparator<Object>() {
			
			@Override
			public int compare(Object o1, Object o2) {
				Jogador jog1 = (Jogador) o1;
				Jogador jog2 = (Jogador) o2;
				
				if (jog1.getTempo() < jog2.getTempo()) {
					return -1;
				}else if (jog1.getTempo() > jog2.getTempo()){
					return 1;
				}else {
					return 0;					
				}
			}
		});
		for (int i = 0; i < jogadores.size(); i++) {
			jogadoresClassificados += i + " - Nome: " + jogadores.get(i).getNome() + " || " + "Tempo: " + jogadores.get(i).getTempo() + "\n";
		}
		return jogadoresClassificados;
	}
}
