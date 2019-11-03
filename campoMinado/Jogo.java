package campoMinado;

import java.util.ArrayList;

public class Jogo extends Tabuleiro{
	private Jogador jogador; 
	
	public Jogo (int eixoX, int eixoY) {
		super(eixoX, eixoY);
	}
	
	public Jogador getJogador () {
		return jogador;
	}

	public void setJogador (Jogador jogador) {
		this.jogador = jogador;
	}

	public void jogada (TipoJogada tipoJogada, Coordenada coordenada) {
				
		if (tipoJogada.equals(TipoJogada.revelarZona)) {
			
			percorreVizinhosVazios(coordenada);
			
		}else if (tipoJogada.equals(TipoJogada.marcarZonaBomba)) {
						
		}
	}
	
	public void percorreVizinhosVazios (Coordenada coordenada) {
		
		if (getTabuleiro()[coordenada.getEixoX()][coordenada.getEixoY()].getEstado().equals(Estado.VAZIO)) {
			
			getTabuleiro()[coordenada.getEixoX()][coordenada.getEixoY()].setEstadoZona(EstadoZona.REVELADO);
			
			ArrayList<Coordenada> vizinhosProx = vizinhos(coordenada);
			
			for (Coordenada i : vizinhosProx) {
				
				if (getTabuleiro()[i.getEixoX()][i.getEixoY()].getEstado().equals(Estado.VAZIO) && getTabuleiro()[i.getEixoX()][i.getEixoY()].getEstadoZona().equals(EstadoZona.ESCONDIDO)) {
					
					percorreVizinhosVazios(i);
					
				}else if (getTabuleiro()[i.getEixoX()][i.getEixoY()].getEstado().equals(Estado.PERIGO)) {
					
					getTabuleiro()[i.getEixoX()][i.getEixoY()].setEstadoZona(EstadoZona.REVELADO);
					
				}
			}
		}
	}
}
