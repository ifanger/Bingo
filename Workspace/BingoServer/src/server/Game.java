package server;

import java.util.ArrayList;

import game.PlayerHandler;
import protocol.Cartela;
import protocol.Player;
import threads.ServerThread;

public class Game extends Thread {
	public static final int PLAYER_LIMIT	= 10;
	public static final int SORT_DELAY		= 5;
	public static final int START_TIME		= 10;
	
	private ArrayList<Player> playerList;
	private int currentCountDownTime = 0;
	private boolean started = false;
	
	public Game(ServerThread server)
	{
		this.playerList = new ArrayList<Player>();
	}
	
	public void startCountDown()
	{
		while(currentCountDownTime < START_TIME)
		{
			try {
				Thread.sleep(1000 * 60);
				currentCountDownTime++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.start();
	}
	
	public void start()
	{
		if(this.started)
		{
			System.out.println("Tentativa de iniciar um jogo já iniciado.");
			return;
		}
		
		this.started = true;
	}
	
	public void end()
	{
		this.started = false;
	}
	
	public boolean isFull()
	{
		return this.playerList.size() >= Game.PLAYER_LIMIT;
	}
	
	public boolean isGameStarted()
	{
		return this.started;
	}
	
	public void onPlayerJoined(PlayerHandler player)
	{
		if(this.isGameStarted())
		{
			// Expulsa o jogador da sala
			//player.kick();
			return;
		}
		
		// Gera uma cartela para o jogador
		//player.setCartela(new Cartela());
	}
	
	public void onPlayerNumberPick(PlayerHandler player, int number)
	{
		
	}
}
