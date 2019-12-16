package tabuleiro;

/**
 * Classe que representa uma zona no tabuleiro, ou seja, 
 * uma coordenada no plano cartesiano.
 * @author lps
 */
public class Zona {
	private Estado estado;
	private EstadoZona estadoZona;
	private Coordenada coordenada;
	private int numeroBombasProximas = 0;
	
	public Zona () {
	}
	
	/**
	 * Irá ser criado uma zona de acordo com seu estado e sua coordenada.
	 * @param coordenada - coordenada contendo as posições x, y.
	 */
	public Zona (Coordenada coordenada) {
		this(Estado.VAZIO, EstadoZona.ESCONDIDO, coordenada);
	}
	
	/**
	 * Irá ser criado uma zona de acordo com seu estado e sua coordenada.
	 * @param estado - estado da zona, podendo ser: VAZIO, BOMBA, PERIGO.
	 * @param coordenada - coordenada contendo as posições x, y.
	 */
	public Zona (Estado estado, EstadoZona estadoZona,Coordenada coordenada) {
		this.coordenada = coordenada;
		this.estadoZona = estadoZona;
		setEstado(estado);
	}
	
	/**
	 * @return - Retorna o estado da zona
	 */
	public Estado getEstado() {
		return estado;
	}
	
	/**
	 * Coloca o estado da zona, se caso o estado for VAZIO, 
	 * irá ser incrementado em +1 a variável numeroBombasProximas
	 * para demonstrar quantas bombas existentes ao redor da zona.
	 * @param estado - Estado da Zona que irá ser colocado.
	 */
	public void setEstado(Estado estado) {
		if (estado.equals(Estado.PERIGO)) {
			this.numeroBombasProximas += 1;
		}
		
		this.estado = estado;
	}
	
	/**
	 * @return - Retorna o estado da zona, sendo VAZIO ou ESCONDIDO.
	 */
	public EstadoZona getEstadoZona() {
		return estadoZona;
	}
	
	/**
	 * Altera/adiciona o estado da zona, sendo VAZIO ou ESCONDIDO.
	 */
	public void setEstadoZona(EstadoZona estadoZona) {
		this.estadoZona = estadoZona;
	}

	/**
	 * @return - Retorna a coordenada da zona.
	 */
	public Coordenada getCoordenada() {
		return coordenada;
	}
	
	/**
	 * Altera ou adiciona uma coordenada a zona
	 * @param cordenada - coordenada a ser adicionada/alterada.
	 */
	public void setCoordenada(Coordenada cordenada) {
		this.coordenada = cordenada;
	}
	
	/**
	 * @return - retorna o numero de bombas próximas de uma
	 * determinada zona.
	 */
	public int getNumeroBombasProximas() {
		return numeroBombasProximas;
	}
}
