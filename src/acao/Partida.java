package acao;

import java.util.ArrayList;

import tabuleiro.Coordenada;
import tabuleiro.Estado;
import tabuleiro.EstadoZona;
import tabuleiro.Jogador;
import tabuleiro.Tabuleiro;
import tabuleiro.Zona;

/**
 * Classe responsável pela ações do jogador na partida
 * @author lps
 */
public class Partida {
	private Jogador jogador; 
	private Tabuleiro tabuleiro;
	private StatusPartida statusPartida = StatusPartida.ANDAMENTO;
	
	public Partida () {
		this(4, 4);
	}
	
	public Partida (int eixoX, int eixoY) {
		this(eixoX, eixoY, null);		
	}

	public Partida (int eixoX, int eixoY, Jogador jogador) {
		this.tabuleiro = new Tabuleiro(eixoX, eixoY);
		this.jogador = jogador;
	}
	
	/**
	 * @return - retorna o status da partida.
	 */
	public StatusPartida getStatusPartida() {
		return statusPartida;
	}
	
	/**Adiciona/modifica o status da partida com a classe do tipo enum.
	 * @param statusPartida - Status da partida.
	 */
	private void setStatusPartida(StatusPartida statusPartida) {
		this.statusPartida = statusPartida;
	}
	
	/**
	 * Funcão em que inicia o jogo e constroi um tabuleiro.
	 */
	public void iniciarJogo() {
		tabuleiro.construirTabuleiro();
	}
	
	/**
	 * @return - retorna um tabuleiro.
	 */
	public Tabuleiro getTabuleiro() {
		return this.tabuleiro;
	}
	
	/**
	 * @return - Retorna um jogador.
	 */
	public Jogador getJogador() {
		return jogador;
	}
	
	/** Adiciona/Modifica um jogador.
	 * @param jogador - jogador a ser modificado/adicionado.
	 */
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	/**
	 * Função responsavel por processar as jogadas feitas pelo jogador
	 * @param tipoJogada - Coloca o tipo de jogada a ser efetuada com a classe enum TipoJogada.
	 * @param coordenada
	 */
	public void jogada (TipoJogada tipoJogada, Coordenada coordenada) {
				
		if (tipoJogada.equals(TipoJogada.revelarZona)) {
			
			if (tabuleiro.getCampo()[coordenada.getEixoX()][coordenada.getEixoY()].getEstado().equals(Estado.VAZIO)) {
				percorreVizinhosVazios(coordenada);
			
			}else {
				tabuleiro.getCampo()[coordenada.getEixoX()][coordenada.getEixoY()].setEstadoZona(EstadoZona.REVELADO);
				
				if (tabuleiro.getCampo()[coordenada.getEixoX()][coordenada.getEixoY()].getEstado().equals(Estado.BOMBA)) {
					for (Zona[] i : tabuleiro.getCampo()) {
						for (Zona j : i) {
							if (j.getEstado().equals(Estado.BOMBA)) {
								j.setEstadoZona(EstadoZona.REVELADO);
							}
						}
					}
					setStatusPartida(StatusPartida.PERDEU);
					jogador.setStatus(StatusPartida.PERDEU);
				}
			}
			
			checkVitoria();
			
		}else if (tipoJogada.equals(TipoJogada.marcarZonaBomba)) {
			tabuleiro.getCampo()[coordenada.getEixoX()][coordenada.getEixoY()].setEstadoZona(EstadoZona.MARCARBOMBA);
		
		}else if (tipoJogada.equals(TipoJogada.desmarcarZonaBomba)) {
			tabuleiro.getCampo()[coordenada.getEixoX()][coordenada.getEixoY()].setEstadoZona(EstadoZona.ESCONDIDO);
		}
	}
	
	/**
	 * Funcao que percorre os vizinhos vazios adjacentes.
	 * @param coordenada - cordenada a qual a funcao irá percorrer os vizinhos.
	 */
	private void percorreVizinhosVazios (Coordenada coordenada) {
		
		if (tabuleiro.getCampo()[coordenada.getEixoX()][coordenada.getEixoY()].getEstado().equals(Estado.VAZIO)) {
			
			tabuleiro.getCampo()[coordenada.getEixoX()][coordenada.getEixoY()].setEstadoZona(EstadoZona.REVELADO);
			
			ArrayList<Coordenada> vizinhosProx = tabuleiro.vizinhos(coordenada);
			
			for (Coordenada i : vizinhosProx) {
				
				if (tabuleiro.getCampo()[i.getEixoX()][i.getEixoY()].getEstado().equals(Estado.VAZIO) && tabuleiro.getCampo()[i.getEixoX()][i.getEixoY()].getEstadoZona().equals(EstadoZona.ESCONDIDO)) {
					
					percorreVizinhosVazios(i);
					
				}else if (tabuleiro.getCampo()[i.getEixoX()][i.getEixoY()].getEstado().equals(Estado.PERIGO)) {
					
					tabuleiro.getCampo()[i.getEixoX()][i.getEixoY()].setEstadoZona(EstadoZona.REVELADO);
					
				}
			}
		}
	}
	
	/**
	 * funcao responsavel por checkar se o usuário ganhou
	 */
	private void checkVitoria () {
		int cont = 0;
		
		for (Zona[] i : tabuleiro.getCampo()) {
			for (Zona j : i) {
				if (j.getEstadoZona().equals(EstadoZona.REVELADO) && !j.getEstado().equals(Estado.BOMBA)) {
					cont += 1;
				}
			}
		}
		
		if (cont == ((tabuleiro.getDimensao()[0] * tabuleiro.getDimensao()[1]) - tabuleiro.getNumBombas())) {
			setStatusPartida(StatusPartida.GANHOU);
			jogador.setStatus(StatusPartida.GANHOU);
		}
	}
}
