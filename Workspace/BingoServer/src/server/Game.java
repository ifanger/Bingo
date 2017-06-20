package server;

import java.util.ArrayList;

import game.BingoThread;
import game.PlayerHandler;
import protocol.Cartela;
import protocol.GFProtocol;
import protocol.Player;
import threads.ServerThread;

public class Game extends Thread {
	public static final int PLAYER_LIMIT	= 10;
	public static final int SORT_DELAY		= 5;
	public static final int START_TIME		= 10;
	public static final int COUNT_DOWN_TIME	= 60;
	
	private ArrayList<PlayerHandler> playerList;
	private int currentCountDownTime = 0;
	private boolean started = false;
	private ArrayList<Integer> drawnNumbers = new ArrayList<Integer>();
	
	public Game(ServerThread server)
	{
		this.playerList = new ArrayList<PlayerHandler>();
	}
	
	@Override
	public void run()
	{
		startCountDown();
	}
	
	public synchronized void startCountDown()
	{
		while(currentCountDownTime < START_TIME)
		{
			try {
				Thread.sleep(1000 * COUNT_DOWN_TIME);
				currentCountDownTime++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.startGame();
	}
	
	public synchronized void startGame()
	{
		if(this.started)
		{
			System.out.println("Tentativa de iniciar um jogo jÃ¡ iniciado.");
			return;
		}
		
		this.started = true;
		
		// Thread responsável pelos sorteios
		BingoThread sortThread = new BingoThread(this);
		sortThread.start();
	}
	
	public synchronized void end()
	{
		this.started = false;
	}
	
	public synchronized boolean isFull()
	{
		return this.playerList.size() >= Game.PLAYER_LIMIT;
	}
	
	public synchronized boolean isGameStarted()
	{
		return this.started;
	}
	
	public synchronized void broadcastPacket(String packet)
	{
		for(PlayerHandler player : playerList)
			player.sendMessage(packet);
	}
	
	public synchronized void onNumberSorted(int sortedNumber)
	{
		this.broadcastPacket(String.format(GFProtocol.SORT_NUMBER, sortedNumber));
	}
	
	public ArrayList<Integer> getDrawnNumbers() {
		return drawnNumbers;
	}

	public void setDrawnNumbers(ArrayList<Integer> drawnNumbers) {
		this.drawnNumbers = drawnNumbers;
	}

	public synchronized void onPlayerJoined(PlayerHandler player)
	{
		if(this.isGameStarted())
		{
			// Expulsa o jogador da sala
			player.kick();
			return;
		}
		
		// Gera uma cartela para o jogador
		player.setCartela(new Cartela());
		playerList.add(player);
	}
	
	public synchronized void onPlayerBingo(PlayerHandler player)
	{
		
	}
}
