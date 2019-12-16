package validação;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import acao.Partida;
import acao.TipoJogada;
import excessões.DimensaoTabuleiroException;
import excessões.JogadaException;
import excessões.NomeJogadorException;
import excessões.TipoJogadaExcetion;
import excessões.ZonaReveladaException;
import tabuleiro.Coordenada;
import tabuleiro.Jogador;

public class UnitTestes {
	
	//testa o nome com minusculo no inicio
	@Test(expected = NomeJogadorException.class)
	public void checkNomeJogadorPrimeiraMinuscula () throws NomeJogadorException {
		Validador.checkNome("luciano");
	}
	
	//testa o nome com 3 letras e todas minusculas
	@Test(expected = NomeJogadorException.class)
	public void checkNomeJogadorMinusculaTresLetras () throws NomeJogadorException {
		Validador.checkNome("ana");
	}
	
	//testa o nome composto com todos minusculos
	@Test(expected = NomeJogadorException.class)
	public void checkNomeJogadorCompostoMinusculo () throws NomeJogadorException {
		Validador.checkNome("luciano lopes");
	}
	
	//testa o nome composto com o segundo nome minusculo
	@Test(expected = NomeJogadorException.class)
	public void checkNomeJogadorSegundoMin () throws NomeJogadorException {
		Validador.checkNome("Luciano lopes");
	}
	
	//testa o nome composto com o primeiro nome minusculo e segundo nome maiusculo
	@Test(expected = NomeJogadorException.class)
	public void checkNomeJogadorPrimeiroMin () throws NomeJogadorException {
		Validador.checkNome("luciano Lopes");
	}
	
	//testa um nome correto
	@Test
	public void checaNomeCorreto () throws NomeJogadorException{
		assertTrue(Validador.checkNome("Luciano"));
	}
	
	//testa um nome com tres letras correto
	@Test
	public void checaNomeCorretoTresLetras () throws NomeJogadorException{
		assertTrue(Validador.checkNome("Ana"));
	}
	
	//testa o nome composto correto
	@Test
	public void checaNomeCorretoComposto () throws NomeJogadorException{
		assertTrue(Validador.checkNome("Luciano Lopes"));
	}
	
	//testa uma dimensao invalida menor que a permitido no eixo x
	@Test(expected = DimensaoTabuleiroException.class)
	public void checkDimensaoInvalidaMenorPrimeiro () throws DimensaoTabuleiroException{
		Validador.checkDimensao(7, 8);
	}
	
	//testa uma dimensao invalida menor que a permitido no eixo y
	@Test(expected = DimensaoTabuleiroException.class)
	public void checkDimensaoInvalidaMenorSegundo () throws DimensaoTabuleiroException{
		Validador.checkDimensao(8, 7);
	}
	
	//testa uma jogada invalida maior que o tabuleiro no eixo x
	@Test(expected = JogadaException.class)
	public void checkJogadaInvalidaMaiorX () throws JogadaException{
		Jogador jogador = new Jogador("Luciano");
		Partida jogo = new Partida(8,8,jogador);
		
		Validador.checkJogada(10, 7, 8, 8, jogo);
	}
	
	//testa uma jogada invalida maior que o tabuleiro no eixo y
	@Test(expected = JogadaException.class)
	public void checkJogadaInvalidaMaiorY () throws JogadaException{
		Jogador jogador = new Jogador("Luciano");
		Partida jogo = new Partida(8,8,jogador);
		
		Validador.checkJogada(7, 10, 8, 8, jogo);
	}
	
	//testa uma jogada invalida maior que o tabuleiro no eixo x e y
	@Test(expected = JogadaException.class)
	public void checkJogadaInvalidaMaiorXY () throws JogadaException{
		Jogador jogador = new Jogador("Luciano");
		Partida jogo = new Partida(8,8,jogador);
		
		Validador.checkJogada(10, 10, 8, 8, jogo);
	}
	
	//testa uma jogada valida no range do tabuleiro
	@Test
	public void checkJogadaValida () throws JogadaException {
		Jogador jogador = new Jogador("Luciano");
		Partida jogo = new Partida(8,8,jogador);
		jogo.iniciarJogo();
		
		assertTrue(Validador.checkJogada(1, 5, 8, 8, jogo));
	}
	
	//testa uma jogada valida no limite do tabuleiro
	@Test
	public void checkJogadaValidaLimiteXY () throws JogadaException {
		Jogador jogador = new Jogador("Luciano");
		Partida jogo = new Partida(8,8,jogador);
		jogo.iniciarJogo();
		
		//teste 8,8 = 7,7, contando com 0
		assertTrue(Validador.checkJogada(7, 7, 8, 8, jogo));
	}
	
	//testa uma jogada de zona ja revelada no tabuleiro
	@Test(expected = ZonaReveladaException.class)
	public void checkJogadaZonaRevelada () throws ZonaReveladaException {
		Jogador jogador = new Jogador("Luciano");
		Partida jogo = new Partida(8,8,jogador);
		jogo.iniciarJogo();
		jogo.jogada(TipoJogada.revelarZona, new Coordenada(5, 5));
		
		assertTrue(Validador.checkZonaRevelada(5, 5, jogo));
	}
	
	//testa se caso a opcao for menor que a esperado, lanca uma excetion
	@Test(expected = TipoJogadaExcetion.class)
	public void checkTipoJogadaInvalidaMenor () throws TipoJogadaExcetion {
		Validador.checkTipoJogada(0);
	}
	
	//testa se caso a opcao for maior que a esperado, lanca uma excetion
	@Test(expected = TipoJogadaExcetion.class)
	public void checkTipoJogadaInvalidaMaior () throws TipoJogadaExcetion {
		Validador.checkTipoJogada(4);
	}
	
	//testa a opcao 1 como valida
	@Test
	public void checkTipoJogadaValida1 () throws TipoJogadaExcetion {
		assertTrue(Validador.checkTipoJogada(1));
	}
	
	//testa a opcao 2 como valida
	@Test
	public void checkTipoJogadaValida2 () throws TipoJogadaExcetion {
		assertTrue(Validador.checkTipoJogada(2));
	}
	
	//testa a opcao 3 como valida
	@Test
	public void checkTipoJogadaValida3 () throws TipoJogadaExcetion {
		assertTrue(Validador.checkTipoJogada(3));
	}
}
