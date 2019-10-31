package campoMinado;

public class Zona {
	private Estado estado;
	Coordenada coordenada;
	
	public Zona () {
	}
	
	public Zona (Estado estado, Coordenada coordenada) {
		this.estado = estado;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Coordenada getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(Coordenada cordenada) {
		this.coordenada = cordenada;
	}
}
