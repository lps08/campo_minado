package campoMinado;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Jogo jogo = new Jogo(8, 8);
		Scanner sc = new Scanner(System.in);
		String nomeJogador;
		int tamanho;
		
		System.out.println("-------------------------");
		System.out.println("----Jogo campo minado----");
		System.out.println("-------------------------");
		System.out.println("");
		System.out.print("Nome jogador: ");
		nomeJogador = sc.nextLine();
		System.out.print("Tamanho tabuleiro: ");
	}
}
