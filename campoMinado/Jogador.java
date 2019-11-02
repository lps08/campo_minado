package campoMinado;

/**
 * Classe que irá armazenar os dados de um jogador.
 * @author lps
 */
public class Jogador {
	private String nome;
	
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
}