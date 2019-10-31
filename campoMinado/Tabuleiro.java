package campoMinado;

import java.util.ArrayList;
import java.util.Random;

public class Tabuleiro {
	
	private int[] dimensao = new int[2];
	private Zona[][] tabuleiro;
	
	public Tabuleiro (int eixoX, int eixoY) {
		tabuleiro = new Zona[eixoX][eixoY];
		dimensao[0] = eixoX;
		dimensao[1] = eixoY;
	}
	
	public int[] getDimensao() {
		return dimensao;
	}
	
	public void setDimensao(int eixoX, int eixoY) {
		tabuleiro = new Zona[eixoX][eixoY];
		dimensao[0] = eixoX;
		dimensao[1] = eixoY;
	}
	
	public Zona[][] getTabuleiro() {
		return tabuleiro;
	}
	
	public void construirTabuleiro () {
		
		Random random = new Random();
		ArrayList<Coordenada> vizinhosPerigo = new ArrayList<Coordenada>();
		int bombas = (int) ((dimensao[0] * dimensao[1]) * 0.16);
		
		for (int i = 0; i < dimensao[0]; i++) {
			for (int j = 0; j < dimensao[1]; j++) {
				tabuleiro[i][j] = new Zona(Estado.VAZIO, new Coordenada(i, j));
			}
		}
		
		for (int i = 0; i < bombas; i ++) {
			int eixoX = random.nextInt(dimensao[0]);
			int eixoY = random.nextInt(dimensao[1]);
			
			vizinhosPerigo = zonaPerigoBomba(new Coordenada(eixoX, eixoY));
			for (Coordenada coord : vizinhosPerigo) {
				if (tabuleiro[coord.getEixoX()][coord.getEixoY()].getEstado().equals(Estado.VAZIO)) {
					tabuleiro[coord.getEixoX()][coord.getEixoY()].setEstado(Estado.PERIGO);
				}
			}
			
			System.out.println("X: " + eixoX + " | " + "Y: " + eixoY);
			
			tabuleiro[eixoX][eixoY] = new Zona(Estado.BOMBA, new Coordenada(eixoX, eixoY));
		}
	}
	
	public void mostraTabuleiro () {
		for(Zona[] i: tabuleiro) {
			for (Zona j : i) {
				if (j.getEstado().equals(Estado.BOMBA)) {
					System.out.print("[B]");					
				}else if (j.getEstado().equals(Estado.PERIGO)){
					System.out.print("[~]");					
				}else {
					System.out.print("[ ]");
				}
			}
			System.out.println("\n");
		}
	}
	
	public ArrayList<Coordenada> zonaPerigoBomba (Coordenada coord) {
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
		
		for (Coordenada i : vizinhos) System.out.println("[" + i.getEixoX() + "]" + "[" + i.getEixoY() + "]");
		
		return vizinhos;
	}
}
