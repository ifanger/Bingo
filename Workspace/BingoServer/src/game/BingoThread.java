package game;

import java.util.ArrayList;
import java.util.Random;

import protocol.Cartela;
import server.Game;

/**
 * Essa Thread é responsável por sortear novos números no bingo.
 * @author Gustavo Ifanger
 *
 */
public class BingoThread extends Thread {
	private Game game;
	
	/**
	 * O construtor recebe o jogo como parâmetro.
	 * @param game Jogo atual.
	 */
	public BingoThread(Game game)
	{
		this.game = game;
	}

	@Override
	public void run() {
		
		while(game.isGameStarted())
		{
			// Aguarda 5 segundos
			try {
				Thread.sleep(1000 * Game.SORT_DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			int n = Cartela.getRandomNumber();
			while(this.game.getDrawnNumbers().contains(n))
			{
				n = Cartela.getRandomNumber();
			}
			
			this.game.getDrawnNumbers().add(n);
			this.game.onNumberSorted(n);
		}
		
	}

}
