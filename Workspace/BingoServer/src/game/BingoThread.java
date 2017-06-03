package game;

import java.util.ArrayList;
import java.util.Random;

import protocol.Cartela;

/**
 * Essa Thread é responsável por sortear novos números no bingo.
 * @author Gustavo Ifanger
 *
 */
public class BingoThread extends Thread {
	private Game game;
	private ArrayList<Integer> drawnNumbers = new ArrayList<Integer>();
	
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
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			int n = Cartela.getRandomNumber();
			while(drawnNumbers.contains(n))
				n = Cartela.getRandomNumber();
			
			
		}
		
	}

}
