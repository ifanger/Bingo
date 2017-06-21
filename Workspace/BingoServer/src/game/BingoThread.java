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
	private ArrayList<Integer> numbersToSort;
	
	/**
	 * O construtor recebe o jogo como parâmetro.
	 * @param game Jogo atual.
	 */
	public BingoThread(Game game)
	{
		this.game = game;
		this.numbersToSort = new ArrayList<Integer>();
		
		for(int i = 0; i < 98; i++)
		{
			int n = Cartela.getRandomNumber();
			while(this.numbersToSort.contains(n))
				n = Cartela.getRandomNumber();
			
			this.numbersToSort.add(n, i);
			System.out.println("Adicionado à lista de números: " + n);
		}
		
		System.out.println(this.numbersToSort.toString());
	}

	@Override
	public void run() {
		
		while(game.isGameStarted())
		{
			try {
				Thread.sleep(1000 * Game.SORT_DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(!game.isGameStarted())
				return;
			
			int n = this.numbersToSort.get(0);
			this.game.getDrawnNumbers().add(n);
			this.game.onNumberSorted(n);
			this.numbersToSort.remove(0);
		}
		
	}

}
