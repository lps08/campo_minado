package campoMinado;

public class Coordenada {
	private int eixoX;
	private int eixoY;
	
	public Coordenada () {
	}
	
	public Coordenada (int eixoX, int eixoY) {
		this.eixoX = eixoX;
		this.eixoY = eixoY;
	}

	public int getEixoX() {
		return eixoX;
	}

	public void setEixoX(int eixoX) {
		this.eixoX = eixoX;
	}

	public int getEixoY() {
		return eixoY;
	}

	public void setEixoY(int eixoY) {
		this.eixoY = eixoY;
	}
	
	public void mostraCoord() {
		System.out.print("[" + eixoX + "]" + "[" + eixoY + "]\n");
	}
}
