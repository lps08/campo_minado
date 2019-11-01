package campoMinado;

public class Zona {
	private Estado estado;
	private Coordenada coordenada;
	private int numeroBombasProximas = 0;
	
	public Zona () {
	}
	
	public Zona (Estado estado, Coordenada coordenada) {
		this.coordenada = coordenada;
		setEstado(estado);
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		if (estado.equals(Estado.PERIGO)) {
			this.numeroBombasProximas += 1;
		}
		
		this.estado = estado;
	}

	public Coordenada getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(Coordenada cordenada) {
		this.coordenada = cordenada;
	}

	public int getNumeroBombasProximas() {
		return numeroBombasProximas;
	}

	public void setNumeroBombasProximas(int numeroBombasProximas) {
		this.numeroBombasProximas = numeroBombasProximas;
	}
}
