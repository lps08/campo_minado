package campoMinado;

import acao.Partida;
import acao.StatusPartida;
import acao.TipoJogada;
import tabuleiro.Coordenada;
import tabuleiro.Jogador;
import ui.InterfaceUsuario;

/**
 * Classe principal em que representa o motor do jogo.
 * @author lps
 */
public class Main {
	
	public static void main(String[] args) {
		
		int cont = 1;
		double time1, time2;
		Jogador jogador;
		
		InterfaceUsuario.inicioJogo();
		
		do {
			jogador = new Jogador(InterfaceUsuario.getNomeJogador());
			Integer[] tamanho = InterfaceUsuario.getTamanhoTabuleiro();
			
			Partida partida = new Partida(tamanho[0], tamanho[1], jogador);
			partida.iniciarJogo();

			time1 = System.currentTimeMillis();
			
			while (true) {
				
				System.out.println("-------------------------");
				InterfaceUsuario.mostraTabuleiro2(partida.getTabuleiro());
				InterfaceUsuario.mostraTabuleiro(partida.getTabuleiro());
				System.out.println("-------------------------");
				
				int tipoJogada = InterfaceUsuario.getTipoJogada();
				Integer[] jogada = InterfaceUsuario.getJogada(cont, tamanho, partida);
				
				if (tipoJogada == 1) {
					
					partida.jogada(TipoJogada.revelarZona, new Coordenada(jogada[0], jogada[1]));
					
					if (partida.getStatusPartida().equals(StatusPartida.PERDEU)) {
						System.out.println("-------------------------");
						InterfaceUsuario.mostraTabuleiro(partida.getTabuleiro());
						System.out.println("-------------------------");
						System.out.println("\nGAME OVER\n");
						
						time2 = System.currentTimeMillis();
						break;
					
					}else if (partida.getStatusPartida().equals(StatusPartida.GANHOU)) {
						System.out.println("-------------------------");
						InterfaceUsuario.mostraTabuleiro2(partida.getTabuleiro());
						System.out.println("-------------------------");
						System.out.println("\nGANHOU\n");
						
						time2 = System.currentTimeMillis();
						break;
					}
				
				}else if (tipoJogada == 2) {
					partida.jogada(TipoJogada.marcarZonaBomba, new Coordenada(jogada[0], jogada[1]));
				
				}else {
					partida.jogada(TipoJogada.desmarcarZonaBomba, new Coordenada(jogada[0], jogada[1]));
				}
				
				cont += 1;
			}
			jogador.setTempo((time2 - time1) / 1000);
			Classificacao.getInstace().addJogador(jogador);;
			
			System.out.println("Tempo de partida: " + ((time2 - time1) / 1000) + " Segundos");
		
		}while (InterfaceUsuario.getFimJogo() != 2);
		
		InterfaceUsuario.mostraClassificacao();
	}
}
