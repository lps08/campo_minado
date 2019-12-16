package tabuleiro;

import acao.StatusPartida;

/**
 * Classe que irá armazenar os dados de um jogador.
 * @author lps
 */
public class Jogador {
	private String nome;
	private double tempo;
	private StatusPartida status = null;
	
	/**
	 * Construtor sem parâmetro em que será necessario settar o jogador depois, pois o mesmo irá com o valor null.
	 */
	public Jogador () {
		this(null);
	}
	
	/**
	 * Adiciona o nome de um jogador.
	 * @param nome - String com o nome.
	 */
	public Jogador (String nome) {
		this.nome = nome;
	}
	
	/**
	 * @return - Retorna uma String com o nome do jogador.
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Adiciona/Altera o nome de um jogador.
	 * @param nome - String com o nome.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @return - o tempo do jogador que ganhou a ultima partida.
	 */
	public double getTempo() {
		return tempo;
	}
	
	/**
	 * Adiciona/Altera o tempo de um jogador.
	 * @param nome - String com o nome.
	 */
	public void setTempo(double tempo) {
		this.tempo = tempo;
	}
	
	/**
	 * @return - Retorna o status da partida
	 */
	public StatusPartida getStatus() {
		return status;
	}
	
	/**
	 * Adicona/modifica o status da partida.
	 * @param status - status do jogo.
	 */
	public void setStatus(StatusPartida status) {
		this.status = status;
	}
}