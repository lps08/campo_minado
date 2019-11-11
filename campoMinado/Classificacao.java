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
	 * Imprime a listas de jogadores junto com o tempo
	 */
	public void mostraClassificacao () {
		//falta sortear
		double menor = 999999999;
		Jogador jogadorMenorTempo = null;
		for (int i = 0; i < jogadores.size(); i++) {
			for (Jogador j : jogadores) {
				if (j.getTempo() < menor) {
					menor = j.getTempo();
					jogadorMenorTempo = j;					
				}
				System.out.println("Nome: " + jogadorMenorTempo.getNome() + " | " + "Tempo: " + jogadorMenorTempo.getTempo() + " segundos");
				jogadores.remove(jogadorMenorTempo);
			}
		}
	}
	
}
