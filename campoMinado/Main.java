package campoMinado;

import java.util.Scanner;

public class Main {
	
	
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int cont = 1, resp;
		double time1, time2;
		
		System.out.println("-------------------------");
		System.out.println("----Jogo campo minado----");
		System.out.println("-------------------------");
		System.out.println("");
		
		do {
			System.out.print("Nome jogador: ");
			String nomeJogador = sc.next();
			System.out.print("Tamanho tabuleiro no eixo X e Y [ex: 8,8]: ");
			String tamanho = sc.next();
			
			Jogo jogo = new Jogo(Integer.parseInt(tamanho.split(",")[0]), Integer.parseInt(tamanho.split(",")[1]), nomeJogador);
			jogo.construirTabuleiro();
			
			time1 = System.currentTimeMillis();
			
			while (true) {
				
				System.out.println("-------------------------");
				jogo.mostraTabuleiro2();
				jogo.mostraTabuleiro();
				System.out.println("-------------------------");
				
				System.out.println("Jogada " + cont);
				System.out.println("Coordenada da jogada " + cont + " [ex: 3,3] : ");
				String jogada = sc.next();
				
				if (Integer.parseInt(jogada.split(",")[0]) < jogo.getDimensao()[0] && Integer.parseInt(jogada.split(",")[1]) < jogo.getDimensao()[1]) {
				
					System.out.println("----Tipo de jogada----");
					System.out.println("1 - Revelar zona | 2 - Marcar bomba | 3 - Desmarcar coordenada");
					int tipoJogada = sc.nextInt();
					
					if (tipoJogada == 1) {
						jogo.jogada(TipoJogada.revelarZona, new Coordenada(Integer.parseInt(jogada.split(",")[0]), Integer.parseInt(jogada.split(",")[1])));
						
						if (jogo.getStatusPartida().equals(StatusPartida.PERDEU)) {
							System.out.println("-------------------------");
							jogo.mostraTabuleiro();
							System.out.println("-------------------------");
							System.out.println("GAME OVER");
							
							time2 = System.currentTimeMillis();
							break;
						
						}else if (jogo.getStatusPartida().equals(StatusPartida.GANHOU)) {
							System.out.println("-------------------------");
							jogo.mostraTabuleiro2();
							System.out.println("-------------------------");
							System.out.println("\nGANHOU\n");
							
							time2 = System.currentTimeMillis();
							break;
						}
					
					}else if (tipoJogada == 2) {
						jogo.jogada(TipoJogada.marcarZonaBomba, new Coordenada(Integer.parseInt(jogada.split(",")[0]), Integer.parseInt(jogada.split(",")[1])));
					
					}else {
						jogo.jogada(TipoJogada.desmarcarZonaBomba, new Coordenada(Integer.parseInt(jogada.split(",")[0]), Integer.parseInt(jogada.split(",")[1])));
					}
				
				}else {
					System.out.println("Jogada inválida");
					continue;
				}
				cont += 1;
			}
			
			System.out.println("Tempo de partida: " + ((time2 - time1) / 1000) + " Segundos");
			
			System.out.println("1 - Jogar novamente | 2 - Desistir\n");
			resp = sc.nextInt();
		
		}while (resp != 2);
		
	}
}
