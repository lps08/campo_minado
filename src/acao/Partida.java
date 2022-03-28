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
		// zona atual que irá ser analisada
		Zona zonaAtual = tabuleiro.getCampo()[coordenada.getEixoX()][coordenada.getEixoY()];
				
		if (tipoJogada.equals(TipoJogada.revelarZona)) {
			
			if (zonaAtual.getEstado().equals(Estado.VAZIO)) {
				percorreVizinhos(coordenada);
			
			}else {
				zonaAtual.setEstadoZona(EstadoZona.REVELADO);
				
				if (zonaAtual.getEstado().equals(Estado.BOMBA)) {
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
			zonaAtual.setEstadoZona(EstadoZona.MARCARBOMBA);
		
		}else if (tipoJogada.equals(TipoJogada.desmarcarZonaBomba)) {
			zonaAtual.setEstadoZona(EstadoZona.ESCONDIDO);
		}
	}
	
	/**
	 * Funcao que percorre os vizinhos vazios adjacentes.
	 * @param coordenada - cordenada a qual a funcao irá percorrer os vizinhos.
	 */
	private void percorreVizinhos (Coordenada coordenada) {

		Zona zonaAtual = tabuleiro.getCampo()[coordenada.getEixoX()][coordenada.getEixoY()];
		
		if (zonaAtual.getEstado().equals(Estado.VAZIO)) {
			zonaAtual.setEstadoZona(EstadoZona.REVELADO);
			ArrayList<Coordenada> vizinhosProx = tabuleiro.vizinhos(coordenada);
			
			for (Coordenada i : vizinhosProx) {
				Zona zonaVizinhoProx = tabuleiro.getCampo()[i.getEixoX()][i.getEixoY()];
				
				if (zonaVizinhoProx.getEstado().equals(Estado.VAZIO) && zonaVizinhoProx.getEstadoZona().equals(EstadoZona.ESCONDIDO)) {
					percorreVizinhos(i);
					
				}else if (zonaVizinhoProx.getEstado().equals(Estado.PERIGO)) {
					zonaVizinhoProx.setEstadoZona(EstadoZona.REVELADO);
					
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
