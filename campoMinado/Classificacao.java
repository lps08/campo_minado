package campoMinado;

import java.util.ArrayList;

public class Classificacao {
	
	private Jogador jogador;
	private StatusPartida statusPartida;
	private ArrayList<Jogador> ganhadores = new ArrayList<Jogador>();
	
	public Classificacao(Jogador jogador, StatusPartida statusPartida) {
		this.jogador = jogador;
		this.statusPartida = statusPartida;
		
		ganhadores();
	}
	
	private void ganhadores () {
		if (statusPartida.equals(StatusPartida.GANHOU)) {
			ganhadores.add(jogador);
		}
	}
	
	public void mostraTabela () {
		
		for (Jogador i : ganhadores) {
			System.out.println("---------------");
			System.out.println("Placares:");
			System.out.println("---------------");
			System.out.println("Nome: " + i.getNome() + " | " + "Tempo: " + i.getTempo());
		}
		
	}
	
}
