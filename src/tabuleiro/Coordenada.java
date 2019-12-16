package tabuleiro;

/**
 * Classe em que será responsável pelas coordenadas de uma zona.
 * @author lps
 */
public class Coordenada {
	private int eixoX;
	private int eixoY;
	
	public Coordenada () {
	}
	
	/**
	 * Irá ser adicionados os valores no eixo X e Y de uma coordenada.
	 * @param eixoX - Valor inteiro do eixo X.
	 * @param eixoY - Valor inteiro do eixo Y.
	 */
	public Coordenada (int eixoX, int eixoY) {
		this.eixoX = eixoX;
		this.eixoY = eixoY;
	}
	
	/**
	 * @return - Retorna o eixo x da coordenada.
	 */
	public int getEixoX() {
		return eixoX;
	}
	
	/**
	 * Altera/adiciona um valor para a coordenada X.
	 * @param eixoX - valor inteiro da coordenada X.
	 */
	public void setEixoX(int eixoX) {
		this.eixoX = eixoX;
	}
	
	/**
	 * @return - Retorna o eixo Y da coordenada.
	 */
	public int getEixoY() {
		return eixoY;
	}
	
	/**
	 * Altera/adiciona um valor para a coordenada Y.
	 * @param eixoX - valor inteiro da coordenada Y.
	 */
	public void setEixoY(int eixoY) {
		this.eixoY = eixoY;
	}
	
	/**
	 * Função teste para mostrar uma determinada coordenada.
	 */
	@Override
	public String toString() {
		return "[" + eixoX + "]" + "[" + eixoY + "]\n";
	}
}
