package campoMinado;

import java.util.ArrayList;

/**
 * Classe responsavel por armazenar as classificacao dos jogadores
 * @author lps
 */
public class Classificacao {
	private ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
	
	public Classificacao () {
	}
	
	/**
	 * Metodo que adiciona um jogador para um ArrayList, que armazenara os jogadores vencedores 
	 * @param jogador - Jogador a ser adicionado
	 */
	public void addJogadores (Jogador jogador) {
		jogadores.add(jogador);
	}
	
	/**
	 * Imprime a listas de jogadores
	 */
	public void mostraClassificacao () {
		for (Jogador i : jogadores) {
			System.out.println("Nome: " + i.getNome() + " | " + "Tempo: " + i.getTempo() + " segundos");
		}
	}
	
}
