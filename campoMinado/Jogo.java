package campoMinado;

import java.util.ArrayList;

public class Jogo extends Tabuleiro{
	private Jogador jogador; 
	
	public Jogo (int eixoX, int eixoY, String nomeJogador) {
		super(eixoX, eixoY);
		this.jogador.setNome(nomeJogador);
	}
	
	public Jogador getJogador () {
		return jogador;
	}

	public void setJogador (Jogador jogador) {
		this.jogador = jogador;
	}

	public void jogada (TipoJogada tipoJogada, Coordenada coordenada) {
				
		if (tipoJogada.equals(TipoJogada.revelarZona)) {
			if (getTabuleiro()[coordenada.getEixoX()][coordenada.getEixoY()].getEstado().equals(Estado.VAZIO)) {
				
				ArrayList<Coordenada> vizinhosVazios = new ArrayList<Coordenada>();
				
				for(Coordenada i : vizinhosVazios) {
					
					if (getTabuleiro()[i.getEixoX()][i.getEixoY()].equals(Estado.VAZIO)) {
						//REVELAR A ZONA VAZIA
						//WHILE ADICIONANDO TODOS OS VIZINHOS DE ZONA VAZIA
						ArrayList<Coordenada> vizinhosDosVizinhos = new ArrayList<Coordenada>();
						
						for (Coordenada j : vizinhosDosVizinhos) {
							if (getTabuleiro()[j.getEixoX()][j.getEixoY()].equals(Estado.VAZIO)) {
								//REVELAR A ZONA VAZIA
							}
						}
					}else if (getTabuleiro()[i.getEixoX()][i.getEixoY()].getEstado().equals(Estado.PERIGO)) {
						
						//REVELA QUANTAS BOMBAS EXISTEM NO LUGAR
						
					}else {
						
						//Perdeu
						
					}
				}
			}
		}else if (tipoJogada.equals(TipoJogada.marcarZonaBomba)) {
						
		}
	}
	
	public void revelaVizinhosVaziosPerigo (Coordenada coordenada) {
		
		ArrayList<Coordenada> vizinhanca = new ArrayList<Coordenada>();
		ArrayList<Coordenada> vizinhancaVazio = new ArrayList<Coordenada>();
		vizinhanca = vizinhos(coordenada);
		
		for (Coordenada i : vizinhanca) {
			
			if (getTabuleiro()[i.getEixoX()][i.getEixoY()].getEstado().equals(Estado.VAZIO)) {
				vizinhancaVazio.add(i);
			}
		}
		
		for (Coordenada i : vizinhancaVazio) {
			revelaVizinhosVaziosPerigo(i);
		}
	}
}
