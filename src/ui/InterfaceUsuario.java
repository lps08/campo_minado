package ui;

import java.util.Scanner;

import acao.Partida;
import campoMinado.Classificacao;
import excessões.DimensaoTabuleiroException;
import excessões.JogadaException;
import excessões.NomeJogadorException;
import excessões.RespostaFimException;
import excessões.TipoJogadaExcetion;
import excessões.ZonaReveladaException;
import tabuleiro.Estado;
import tabuleiro.EstadoZona;
import tabuleiro.Tabuleiro;
import tabuleiro.Zona;
import validação.Validador;

/**
 * Classe responsável por coletar as informações do jogador.
 * @author lps
 */
public class InterfaceUsuario {
	
	private static Scanner sc = new Scanner(System.in);
	
	public InterfaceUsuario() {
	}
	
	/**
	 * Cabeçalho simples do jogo campominado.
	 */
	public static void inicioJogo() {
		System.out.println("-------------------------");
		System.out.println("----Jogo campo minado----");
		System.out.println("-------------------------");
		System.out.println("");
	}
	
	/**
	 * Irá coletar o nome do jogador.
	 * @return - String com o nome do jogador validado.
	 */
	public static String getNomeJogador() {
		String nomeJogador;
		
		System.out.print("Nome jogador: ");
		nomeJogador = sc.next();
		
		while (true) {
			try {					
				if (Validador.checkNome(nomeJogador)) {
					break;
				}
			} catch (NomeJogadorException e) {
				System.err.println(e);
				System.out.print("Nome jogador: ");
				nomeJogador = sc.next();
			}
		}
		
		return nomeJogador;
	}
	
	/**
	 * Irá coletar o tamanho do tabuleiro.
	 * @return - Uma lista de duas posições, onde o index 0 representa o eixo y e o index 1 representa o eixo x.
	 */
	public static Integer[] getTamanhoTabuleiro() {
		System.out.print("Tamanho tabuleiro no eixo X e Y [min: 4,4]: ");
		String tamanho = sc.next();
		
		while (true) {
			try {
				if (Validador.checkDimensao(Integer.parseInt(tamanho.split(",")[0]), Integer.parseInt(tamanho.split(",")[1]))) {
					break;
				}
			} catch (DimensaoTabuleiroException e) {
				System.err.println(e);
				System.out.print("Tamanho tabuleiro no eixo X e Y [min: 4,4]: ");
				tamanho = sc.next();
			}
		}
		return new Integer[] {Integer.parseInt(tamanho.split(",")[0]), Integer.parseInt(tamanho.split(",")[1])};
	}
	
	/**
	 * Irá coletar a jogada do jogador.
	 * @param cont - Contador de jogadas que será mostrado para o usuário as rodadas do jogo.
	 * @param tamanho - Tamanho do tabuleiro para que possa verificar os limites das jogadas.
	 * @param partida - Onde será coletados os dados do tabuleiro para que possa fazer a verificação das zonas já liberadas.
	 * @return - Retorna uma lista com duas posições, em que o index 0 representa a cordenada y da jogada e o index 1, a coordenada .
	 */
	public static Integer[] getJogada(int cont, Integer[] tamanho, Partida partida) {
		System.out.println("Coordenada da jogada " + cont + " [ex: 3,3] : ");
		String jogada = sc.next();
		
		while (true) {
			
			try {
				if (Validador.checkJogada(Integer.parseInt(jogada.split(",")[0]), Integer.parseInt(jogada.split(",")[1]), tamanho[0], tamanho[1], partida) 
					&& Validador.checkZonaRevelada(Integer.parseInt(jogada.split(",")[0]), Integer.parseInt(jogada.split(",")[1]), partida))
					break;
			
			} catch (JogadaException e) {
				System.err.println(e);
				
				System.out.println("Jogada " + cont);
				System.out.println("Coordenada da jogada " + cont + " [ex: 3,3] : ");
				jogada = sc.next();
			
			} catch (ZonaReveladaException e) {
				System.err.println(e);
				
				System.out.println("Jogada " + cont);
				System.out.println("Coordenada da jogada " + cont + " [ex: 3,3] : ");
				jogada = sc.next();
			}
		}
		
		return new Integer[] {Integer.parseInt(jogada.split(",")[0]), Integer.parseInt(jogada.split(",")[1])};
	}
	
	/**
	 * Irá coletar o tipo de jogada do jogador.
	 * @return - Retorna um numero inteiro validado representando a jogada escolhida pelo jogador.
	 */
	public static int getTipoJogada() {
		System.out.println("----Tipo de jogada----");
		System.out.println("1 - Revelar zona | 2 - Marcar bomba | 3 - Desmarcar coordenada");
		int tipoJogada = sc.nextInt();
		
		while (true) {
			try {
				if (Validador.checkTipoJogada(tipoJogada)) {
					break;
				}
			} catch (TipoJogadaExcetion e) {
				System.err.println(e);
				
				System.out.println("----Tipo de jogada----");
				System.out.println("1 - Revelar zona | 2 - Marcar bomba | 3 - Desmarcar coordenada");
				tipoJogada = sc.nextInt();
			}
		}
		return tipoJogada;
	}
	
	/**
	 * Irá coletar a resposta ao final de cada partida.
	 * @return - Retorna um inteiro com a opção desejada pelo jogador.
	 */
	public static int getFimJogo() {
		System.out.println("1 - Jogar novamente | 2 - Desistir\n");
		int resp = sc.nextInt();
		
		while (true) {
			try {
				if (Validador.checkRespostaFim(resp)) {
					break;
				}
			}catch (RespostaFimException e) {
				System.err.println(e);
				
				System.out.println("1 - Jogar novamente | 2 - Desistir\n");
				resp = sc.nextInt();
			}
		}
		return resp;
	}
	
	/**
	 * Irá imprimir o tabuleiro no terminal de acordo com as construções feitas.
	 */
	public static void mostraTabuleiro(Tabuleiro tabuleiro) {
		for (int i = 0; i < tabuleiro.getDimensao()[1]; i++) System.out.print("    " + i);
		System.out.println("");
		
		for (int i = 0; i < tabuleiro.getDimensao()[0]; i++) {
			System.out.print(i + " ");
			
			for (Zona j : tabuleiro.getCampo()[i]) {
				if (j.getEstadoZona().equals(EstadoZona.REVELADO)) {
					
					if (j.getEstado().equals(Estado.BOMBA)) {
						System.out.print(" [B] ");		
						
					}else if (j.getEstado().equals(Estado.PERIGO)){
						System.out.print(" [" + j.getNumeroBombasProximas() + "] ");
						
					}else {
						System.out.print(" [ ] ");
					}
					
				}else if (j.getEstadoZona().equals(EstadoZona.MARCARBOMBA)) {
					System.out.print(" [*] ");
				
				}else {
					System.out.print(" [-] ");
				}
			}
			System.out.println("\n");
		}
	}
	
	/**
	 * Irá imprimir todo o tabuleiro revelado.
	 */
	public static void mostraTabuleiro2 (Tabuleiro tabuleiro) {
		
		for (int i = 0; i < tabuleiro.getDimensao()[1]; i++) System.out.print("    " + i);
		System.out.println("");
		
		for (int i = 0; i < tabuleiro.getDimensao()[0]; i++) {
			
			System.out.print(i + " ");
			
			for (Zona j : tabuleiro.getCampo()[i]) {	
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
	 * Irá mostrar a classificação dos jogadores.
	 * @param jogador - Jodador 
	 */
	public static void mostraClassificacao() {
		System.out.println("--------------------");
		System.out.println("---Classificacao----");
		System.out.println("--------------------");
		
		System.out.println(Classificacao.getInstace().toString());
	}
}
