package campoMinado;

import java.util.ArrayList;

public class Jogo extends Tabuleiro{
	private Jogador jogador; 
	private StatusPartida statusPartida = StatusPartida.ANDAMENTO;
	
	public Jogo (int eixoX, int eixoY, String nomeJogador) {
		super(eixoX, eixoY);
		jogador = new Jogador(nomeJogador);
	}
	
	public Jogador getJogador () {
		return jogador;
	}

	public void setJogador (Jogador jogador) {
		this.jogador = jogador;
	}

	public StatusPartida getStatusPartida() {
		return statusPartida;
	}

	private void setStatusPartida(StatusPartida statusPartida) {
		this.statusPartida = statusPartida;
	}

	public void jogada (TipoJogada tipoJogada, Coordenada coordenada) {
				
		if (tipoJogada.equals(TipoJogada.revelarZona)) {
			
			if (getTabuleiro()[coordenada.getEixoX()][coordenada.getEixoY()].getEstado().equals(Estado.VAZIO)) {
				percorreVizinhosVazios(coordenada);
			
			}else {
				getTabuleiro()[coordenada.getEixoX()][coordenada.getEixoY()].setEstadoZona(EstadoZona.REVELADO);
				
				if (getTabuleiro()[coordenada.getEixoX()][coordenada.getEixoY()].getEstado().equals(Estado.BOMBA)) {
					for (Zona[] i : getTabuleiro()) {
						for (Zona j : i) {
							if (j.getEstado().equals(Estado.BOMBA)) {
								j.setEstadoZona(EstadoZona.REVELADO);
							}
						}
					}
					setStatusPartida(StatusPartida.PERDEU);
				}
			}
			
			checkVitoria();
			
		}else if (tipoJogada.equals(TipoJogada.marcarZonaBomba)) {
			getTabuleiro()[coordenada.getEixoX()][coordenada.getEixoY()].setEstadoZona(EstadoZona.MARCARBOMBA);
		
		}else if (tipoJogada.equals(TipoJogada.desmarcarZonaBomba)) {
			getTabuleiro()[coordenada.getEixoX()][coordenada.getEixoY()].setEstadoZona(EstadoZona.ESCONDIDO);
		}
	}
	
	private void percorreVizinhosVazios (Coordenada coordenada) {
		
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
	
	private void checkVitoria () {
		int cont = 0;
		
		for (Zona[] i : getTabuleiro()) {
			for (Zona j : i) {
				if (j.getEstadoZona().equals(EstadoZona.REVELADO) && !j.getEstado().equals(Estado.BOMBA)) {
					cont += 1;
				}
			}
		}
		
		if (cont == ((getDimensao()[0] * getDimensao()[1]) - getNumBombas())) {
			setStatusPartida(StatusPartida.GANHOU);
		}
	}
}
