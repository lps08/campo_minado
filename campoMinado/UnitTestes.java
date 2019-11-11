package campoMinado;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UnitTestes {
	
	Testes testes = new Testes();
	
	//testa o nome com minusculo no inicio
	@Test(expected = MinhasException.class)
	public void checkNomeJogadorPrimeiraMinuscula () throws MinhasException {
		testes.checkNome("luciano");
	}
	
	//testa o nome com 3 letras e todas minusculas
	@Test(expected = MinhasException.class)
	public void checkNomeJogadorMinusculaTresLetras () throws MinhasException {
		testes.checkNome("ana");
	}
	
	//testa o nome composto com todos minusculos
	@Test(expected = MinhasException.class)
	public void checkNomeJogadorCompostoMinusculo () throws MinhasException {
		testes.checkNome("luciano lopes");
	}
	
	//testa o nome composto com o segundo nome minusculo
	@Test(expected = MinhasException.class)
	public void checkNomeJogadorSegundoMin () throws MinhasException {
		testes.checkNome("Luciano lopes");
	}
	
	//testa o nome composto com o primeiro nome minusculo e segundo nome maiusculo
	@Test(expected = MinhasException.class)
	public void checkNomeJogadorPrimeiroMin () throws MinhasException {
		testes.checkNome("luciano Lopes");
	}
	
	//testa um nome correto
	@Test
	public void checaNomeCorreto () throws MinhasException{
		assertTrue(testes.checkNome("Luciano"));
	}
	
	//testa um nome com tres letras correto
	@Test
	public void checaNomeCorretoTresLetras () throws MinhasException{
		assertTrue(testes.checkNome("Ana"));
	}
	
	//testa o nome composto correto
	@Test
	public void checaNomeCorretoComposto () throws MinhasException{
		assertTrue(testes.checkNome("Luciano Lopes"));
	}
	
	//testa uma dimensao invalida menor que a permitido no eixo x
	@Test(expected = MinhasException.class)
	public void checkDimensaoInvalidaMenorPrimeiro () throws MinhasException{
		testes.checkDimensao(7, 8);
	}
	
	//testa uma dimensao invalida menor que a permitido no eixo y
	@Test(expected = MinhasException.class)
	public void checkDimensaoInvalidaMenorSegundo () throws MinhasException{
		testes.checkDimensao(8, 7);
	}
	
	//testa uma jogada invalida maior que o tabuleiro no eixo x
	@Test(expected = MinhasException.class)
	public void checkJogadaInvalidaMaiorX () throws MinhasException{
		Jogador jogador = new Jogador("Luciano");
		Jogo jogo = new Jogo(8,8,jogador);
		
		testes.checkJogada(10, 7, 8, 8, jogo);
	}
	
	//testa uma jogada invalida maior que o tabuleiro no eixo y
	@Test(expected = MinhasException.class)
	public void checkJogadaInvalidaMaiorY () throws MinhasException{
		Jogador jogador = new Jogador("Luciano");
		Jogo jogo = new Jogo(8,8,jogador);
		
		testes.checkJogada(7, 10, 8, 8, jogo);
	}
	
	//testa uma jogada invalida maior que o tabuleiro no eixo x e y
	@Test(expected = MinhasException.class)
	public void checkJogadaInvalidaMaiorXY () throws MinhasException{
		Jogador jogador = new Jogador("Luciano");
		Jogo jogo = new Jogo(8,8,jogador);
		
		testes.checkJogada(10, 10, 8, 8, jogo);
	}
	
	//testa uma jogada valida no range do tabuleiro
	@Test
	public void checkJogadaValida () throws MinhasException {
		Jogador jogador = new Jogador("Luciano");
		Jogo jogo = new Jogo(8,8,jogador);
		jogo.construirTabuleiro();
		
		assertTrue(testes.checkJogada(1, 5, 8, 8, jogo));
	}
	
	//testa uma jogada valida no limite do tabuleiro
	@Test
	public void checkJogadaValidaLimiteXY () throws MinhasException {
		Jogador jogador = new Jogador("Luciano");
		Jogo jogo = new Jogo(8,8,jogador);
		jogo.construirTabuleiro();
		
		//teste 8,8 = 7,7, contando com 0
		assertTrue(testes.checkJogada(7, 7, 8, 8, jogo));
	}
	
	//testa uma jogada de zona ja revelada no tabuleiro
	@Test(expected = MinhasException.class)
	public void checkJogadaZonaRevelada () throws MinhasException {
		Jogador jogador = new Jogador("Luciano");
		Jogo jogo = new Jogo(8,8,jogador);
		jogo.construirTabuleiro();
		jogo.jogada(TipoJogada.revelarZona, new Coordenada(5, 5));
		
		assertTrue(testes.checkJogada(5, 5, 8, 8, jogo));
	}
	
	//testa se caso a opcao for menor que a esperado, lanca uma excetion
	@Test(expected = MinhasException.class)
	public void checkTipoJogadaInvalidaMenor () throws MinhasException {
		testes.checkTipoJogada(0);
	}
	
	//testa se caso a opcao for maior que a esperado, lanca uma excetion
	@Test(expected = MinhasException.class)
	public void checkTipoJogadaInvalidaMaior () throws MinhasException {
		testes.checkTipoJogada(4);
	}
	
	//testa a opcao 1 como valida
	@Test
	public void checkTipoJogadaValida1 () throws MinhasException {
		assertTrue(testes.checkTipoJogada(1));
	}
	
	//testa a opcao 2 como valida
	@Test
	public void checkTipoJogadaValida2 () throws MinhasException {
		assertTrue(testes.checkTipoJogada(2));
	}
	
	//testa a opcao 3 como valida
	@Test
	public void checkTipoJogadaValida3 () throws MinhasException {
		assertTrue(testes.checkTipoJogada(3));
	}
}
