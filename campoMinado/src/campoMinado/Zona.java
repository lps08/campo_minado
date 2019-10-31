package campoMinado;

public class Zona {
	private Estado estado;
	
	public Zona () {
	}
	
	public Zona (Estado estado) {
		this.estado = estado;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
