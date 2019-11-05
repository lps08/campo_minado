package campoMinado;

import java.util.Scanner;

public class Main {
	
	
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int cont = 1, resp;
		double time1, time2;
		Testes testes = new Testes();
		Classificacao classificacao = new Classificacao();
		
		System.out.println("-------------------------");
		System.out.println("----Jogo campo minado----");
		System.out.println("-------------------------");
		System.out.println("");
		
		do {
			System.out.print("Nome jogador: ");
			String nomeJogador = sc.next();
			
			while (nomeJogador.length() < 3) {
				try {
					testes.checkNome(nomeJogador);
				} catch (Exception e) {
					System.err.println(e);
					
					System.out.print("Nome jogador: ");
					nomeJogador = sc.next();
				}
			}
			
			Jogador jogador = new Jogador(nomeJogador);
			
			System.out.print("Tamanho tabuleiro no eixo X e Y [min: 8,8]: ");
			String tamanho = sc.next();
			while (Integer.parseInt(tamanho.split(",")[0]) < 8 || Integer.parseInt(tamanho.split(",")[1]) < 8) {
				try {
					testes.checkDimensao(Integer.parseInt(tamanho.split(",")[0]), Integer.parseInt(tamanho.split(",")[1]));
				} catch (Exception e) {
					System.err.println(e);
					System.out.print("Tamanho tabuleiro no eixo X e Y [min: 8,8]: ");
					tamanho = sc.next();
				}
			}
			
			Jogo jogo = new Jogo(Integer.parseInt(tamanho.split(",")[0]), Integer.parseInt(tamanho.split(",")[1]), jogador);
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
				
				while (Integer.parseInt(jogada.split(",")[0]) >= Integer.parseInt(tamanho.split(",")[0]) || 
						Integer.parseInt(jogada.split(",")[1]) >= Integer.parseInt(tamanho.split(",")[1])) {
					
					try {
						testes.checkJogada(Integer.parseInt(jogada.split(",")[0]), Integer.parseInt(jogada.split(",")[1]), Integer.parseInt(tamanho.split(",")[0]), Integer.parseInt(tamanho.split(",")[1]), jogo);
					
					} catch (Exception e) {
						System.err.println(e);
						
						System.out.println("Jogada " + cont);
						System.out.println("Coordenada da jogada " + cont + " [ex: 3,3] : ");
						jogada = sc.next();
					}
				}
				
				System.out.println("----Tipo de jogada----");
				System.out.println("1 - Revelar zona | 2 - Marcar bomba | 3 - Desmarcar coordenada");
				int tipoJogada = sc.nextInt();
				
				if (tipoJogada == 1 && jogo.getTabuleiro()[Integer.parseInt(jogada.split(",")[0])][Integer.parseInt(jogada.split(",")[1])].getEstadoZona().equals(EstadoZona.REVELADO)) {
					
					while (true) {
						if (jogo.getTabuleiro()[Integer.parseInt(jogada.split(",")[0])][Integer.parseInt(jogada.split(",")[1])].getEstadoZona().equals(EstadoZona.REVELADO)) {
							try {
								testes.checkZonaRevelada(Integer.parseInt(jogada.split(",")[0]), Integer.parseInt(jogada.split(",")[1]), jogo);
							
							} catch (Exception e) {
								System.err.println(e);
								
								System.out.println("Jogada " + cont);
								System.out.println("Coordenada da jogada " + cont + " [ex: 3,3] : ");
								jogada = sc.next();
								
								while (Integer.parseInt(jogada.split(",")[0]) >= Integer.parseInt(tamanho.split(",")[0]) || 
										Integer.parseInt(jogada.split(",")[1]) >= Integer.parseInt(tamanho.split(",")[1])) {
									
									try {
										testes.checkJogada(Integer.parseInt(jogada.split(",")[0]), Integer.parseInt(jogada.split(",")[1]), Integer.parseInt(tamanho.split(",")[0]), Integer.parseInt(tamanho.split(",")[1]), jogo);
									
									} catch (Exception r) {
										System.err.println(r);
										
										System.out.println("Jogada " + cont);
										System.out.println("Coordenada da jogada " + cont + " [ex: 3,3] : ");
										jogada = sc.next();
									}
								}
								
								System.out.println("----Tipo de jogada----");
								System.out.println("1 - Revelar zona | 2 - Marcar bomba | 3 - Desmarcar coordenada");
								tipoJogada = sc.nextInt();
								
							}
						}else break;
					}
				}
				
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
				
				cont += 1;
			}
			
			if (jogo.getStatusPartida().equals(StatusPartida.GANHOU)) {
				jogador.setTempo((time2 - time1) / 1000);
				classificacao.addJogadores(jogador);
			}
			
			System.out.println("Tempo de partida: " + ((time2 - time1) / 1000) + " Segundos");
			
			System.out.println("1 - Jogar novamente | 2 - Desistir\n");
			resp = sc.nextInt();
		
		}while (resp != 2);
		
		System.out.println("--------------------");
		System.out.println("---Classificacao----");
		System.out.println("--------------------");
		classificacao.mostraClassificacao();
		
	}
}
