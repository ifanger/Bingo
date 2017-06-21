package game;

import java.util.ArrayList;

import com.google.gson.Gson;

import protocol.Cartela;
import protocol.GFProtocol;
import protocol.Player;
import threads.ServerThread;

public class Game extends Thread {
	public static final int PLAYER_LIMIT	= 10;
	public static final int SORT_DELAY		= 5;
	public static final int START_TIME		= 10;
	
	private ArrayList<PlayerHandler> playerList;
	private ArrayList<Integer> drawnNumbers = new ArrayList<Integer>();
	private int currentCountDownTime = 0;
	private boolean started = false;
	
	public Game(ServerThread server)
	{
		this.playerList = new ArrayList<PlayerHandler>();
	}
	
	public synchronized void startCountDown()
	{
		while(currentCountDownTime < START_TIME)
		{
			try {
				Thread.sleep(1000 * 60);
				currentCountDownTime++;
				System.out.println((START_TIME - currentCountDownTime) + " minuto(s) para começar!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if(playerList.size() > 1)
			this.start();
		else
		{
			currentCountDownTime = 0;
			this.startCountDown();
		}
	}
	
	public synchronized void start()
	{
		if(this.started)
		{
			System.out.println("Tentativa de iniciar um jogo já iniciado.");
			return;
		}
		
		this.started = true;
	}
	
	public synchronized void end(PlayerHandler winner)
	{
		this.started = false;
		
		String winnerJson = new Gson().toJson((Player) winner);
		
		this.broadcast(String.format(GFProtocol.END_GAME, winnerJson));
	}
	
	public synchronized boolean isFull()
	{
		return this.playerList.size() >= Game.PLAYER_LIMIT;
	}
	
	public synchronized boolean isGameStarted()
	{
		return this.started;
	}
	
	public synchronized void broadcast(String packet)
	{
		for(PlayerHandler player : this.playerList)
			if(player != null)
				player.sendMessage(packet);
	}
	
	public synchronized void disconnect(PlayerHandler player)
	{
		if(player == null)
			return;
		
		playerList.remove(player);
		player.kick();
	}
	
	public synchronized void onPlayerJoined(PlayerHandler player)
	{
		if(player == null)
			return;
		
		if(this.isGameStarted())
		{
			player.kick();
			return;
		}
		
		if(playerList.contains(player))
		{
			player.sendMessage("M/Conta já conectada!");
			player.kick();
			return;
		}
		
		this.playerList.add(player);
		player.setCartela(new Cartela());
	}
	
	public synchronized void onPlayerBingo(PlayerHandler player)
	{
		if(player == null)
			return;
		
		Cartela playerCartela = player.getCartela();
		
		if(playerCartela == null)
			return;
		
		int totalNumbers = 0;
		
		for(int n : playerCartela.getCartela())
		{
			if(this.drawnNumbers.contains(n))
				totalNumbers++;
		}
		
		if(totalNumbers == 25)
			this.end(player);
	}
	
	public synchronized void onDrawnNumber(int number)
	{
		this.drawnNumbers.add(number);
	}
}
