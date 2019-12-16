package tabuleiro;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe que irá gerar um tabuleiro do campo minado de acordo com as dimensões passadas no construtor
 * @author lps
 */
public class Tabuleiro {
	
	private int[] dimensao = new int[2];
	private Zona[][] tabuleiro;
	private int numBombas;
	
	/**
	 * Caso não passe as dimensões do tabuleiro, o mesmo irá ser iniciado com as dimensões mínimas.
	 */
	public Tabuleiro () {
		this(4,4);
	}
	
	/**
	 * Construtor que tem a finalidade de pegar o tamanho do tabuleiro.
	 * @param eixoX - Inteiro representando o tamanho do tabuleiro no eixo x.
	 * @param eixoY - Inteiro representando o tamanho do tabuleiro no eixo y.
	 */
	public Tabuleiro (int eixoX, int eixoY) {
		tabuleiro = new Zona[eixoX][eixoY];
		dimensao[0] = eixoX;
		dimensao[1] = eixoY;
	}
	
	/**
	 * Irá retornar um vetor de contendo as dimensões do tabuleiro.
	 * @return retorna dimensao[eixoX, eixoY]
	 */
	public int[] getDimensao() {
		return dimensao;
	}
	
	/**
	 * Irá setar as dimensões do tabuleiro, sendo o mesmo tendo o tamanho igual a mutiplicação 
	 * de seus eixos, ou seja, eixoX * eixoY.
	 * @param eixoX Quantidades de zonas no eixo X.
	 * @param eixoY Quantidades de zonas no eixo Y.
	 */
	public void setDimensao(int eixoX, int eixoY) {
		tabuleiro = new Zona[eixoX][eixoY];
		dimensao[0] = eixoX;
		dimensao[1] = eixoY;
	}
	
	/**
	 * @return - Retorna a matriz do tabuleiro.
	 */
	public Zona[][] getCampo() {
		return tabuleiro;
	}
	
	/**
	 * Pega o numero de bumbas que o tabuleiro possui.
	 * @return - Número inteiro com a quantidade de bombas.
	 */
	public int getNumBombas() {
		return numBombas;
	}

	/**
	 * Função responsável por construir o tabuleiro dado as dimensões.
	 */
	public void construirTabuleiro () {
		
		ArrayList<Coordenada> vizinhosPerigo = new ArrayList<Coordenada>();
		
		for (int i = 0; i < dimensao[0]; i++) {
			for (int j = 0; j < dimensao[1]; j++) {
				tabuleiro[i][j] = new Zona(Estado.VAZIO, EstadoZona.ESCONDIDO,new Coordenada(i, j));
			}
		}
			
		adicionaBombas(16);
		
		for (Zona[] i : tabuleiro) {
			for (Zona j : i) {
				vizinhosPerigo = vizinhos(j.getCoordenada());
				if (j.getEstado() == Estado.BOMBA) {
					for (Coordenada coord : vizinhosPerigo) {
						if (!tabuleiro[coord.getEixoX()][coord.getEixoY()].getEstado().equals(Estado.BOMBA)) {
							tabuleiro[coord.getEixoX()][coord.getEixoY()].setEstado(Estado.PERIGO);
						}
					}
				}
			}
		}
	}
	
	/**
	 * Função que irá identificar os vizinhos de uma determinada zona.
	 * @param coord - Coordenada para ser identificados seus vizinhos.
	 * @return - Retorna um ArrayList com as posições dos vizinhos de uma coordenada.
	 */
	public ArrayList<Coordenada> vizinhos (Coordenada coord) {
		ArrayList<Coordenada> vizinhos = new ArrayList<Coordenada>();
		ArrayList<Coordenada> vizinhosNulos = new ArrayList<Coordenada>();
		
		vizinhos.add(new Coordenada(coord.getEixoX() - 1, coord.getEixoY()));
		vizinhos.add(new Coordenada(coord.getEixoX() + 1, coord.getEixoY()));
		vizinhos.add(new Coordenada(coord.getEixoX(), coord.getEixoY() - 1));
		vizinhos.add(new Coordenada(coord.getEixoX(), coord.getEixoY() + 1));
		vizinhos.add(new Coordenada(coord.getEixoX() + 1, coord.getEixoY() + 1));
		vizinhos.add(new Coordenada(coord.getEixoX() - 1, coord.getEixoY() - 1));
		vizinhos.add(new Coordenada(coord.getEixoX() - 1, coord.getEixoY() + 1));
		vizinhos.add(new Coordenada(coord.getEixoX() + 1, coord.getEixoY() - 1));
		
		for (Coordenada i : vizinhos) {
			if (i.getEixoX() < 0 || i.getEixoY() < 0 || i.getEixoX() > dimensao[0] - 1 || i.getEixoY() > dimensao[1] - 1) 
				vizinhosNulos.add(i);
		}
		for (Coordenada i : vizinhosNulos) vizinhos.remove(i);
		
		return vizinhos;
	}
	
	/**
	 * Função que irá adicionar as bombas no tabuleiro.
	 * @param porcentagemBombas - Porcentagem de bombas adicionadas no tabuleiro.
	 */
	private void adicionaBombas (double porcentagemBombas) {
		
		Random random = new Random();
		numBombas = (int) ((dimensao[0] * dimensao[1]) * (porcentagemBombas / 100));
		
		for (int i = 0; i < numBombas; i ++) {
			
			int eixoX = random.nextInt(dimensao[0]);
			int eixoY = random.nextInt(dimensao[1]);
			
			for (Zona[] j : tabuleiro) {
				for (Zona k : j) {

					if (k.getCoordenada().getEixoX() != eixoX || k.getCoordenada().getEixoY() != eixoY) {
						tabuleiro[eixoX][eixoY] = new Zona(Estado.BOMBA, EstadoZona.ESCONDIDO, new Coordenada(eixoX, eixoY));
					}
				}
			}
		}
	}
}
