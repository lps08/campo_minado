package campoMinado;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe que irá gerar um tabuleiro do campo minado de acordo com as dimensões passadas no construtor
 * @author lps
 */
public class Tabuleiro {
	
	private int[] dimensao = new int[2];
	private Zona[][] tabuleiro;
	
	public Tabuleiro (int eixoX, int eixoY) {
		tabuleiro = new Zona[eixoX][eixoY];
		dimensao[0] = eixoX;
		dimensao[1] = eixoY;
	}
	
	/**
	 * Irá retornar um vetor de contendo as dimensões do tabuleiro.
	 * 
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
	public Zona[][] getTabuleiro() {
		return tabuleiro;
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
	 * Função que irá printar o tabuleiro no terminal, de acordo com a construção feita.
	 */
	public void mostraTabuleiro () {
		for(Zona[] i: tabuleiro) {
			for (Zona j : i) {
				if (j.getEstadoZona().equals(EstadoZona.REVELADO)) {
					if (j.getEstado().equals(Estado.BOMBA)) {
						System.out.print(" [B] ");					
					}else if (j.getEstado().equals(Estado.PERIGO)){
						System.out.print(" [" + j.getNumeroBombasProximas() + "] ");					
					}else {
						System.out.print(" [ ] ");
					}
				}else {
					System.out.print(" [?] ");
				}
			}
			System.out.println("\n");
		}
	}
	
	public void mostraTabuleiro2 () {
		for(Zona[] i: tabuleiro) {
			for (Zona j : i) {
				if (j.getEstado().equals(Estado.BOMBA)) {
					System.out.print(" [B] ");					
				}else if (j.getEstado().equals(Estado.PERIGO)){
					System.out.print(" [" + j.getNumeroBombasProximas() + "] ");					
				}else {
					System.out.print(" [ ] ");
				}
			}
			System.out.println("\n");
		}
	}
	
	/**
	 * Função que irá identificar os vizinhos de uma determinada zona.
	 * @param coord - Coordenada para ser identificados seus vizinhos.
	 * @return - Retorna um ArrayList com as posições dos vizinhos de uma coordenada.
	 */
	protected ArrayList<Coordenada> vizinhos (Coordenada coord) {
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
			if (i.getEixoX() < 0 || i.getEixoY() < 0 || i.getEixoX() > dimensao[0] - 1
					|| i.getEixoY() > dimensao[1] - 1) 
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
		int bombas = (int) ((dimensao[0] * dimensao[1]) * (porcentagemBombas / 100));
		
		for (int i = 0; i < bombas; i ++) {
			
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
