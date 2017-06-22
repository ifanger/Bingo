package server;

import java.util.ArrayList;

import com.google.gson.Gson;

import daos.Players;
import game.BingoThread;
import game.PlayerHandler;
import protocol.Cartela;
import protocol.GFProtocol;
import protocol.Player;
import threads.ServerThread;

/**
 * Classe gerenciadora do jogo.
 * @author Gustavo Ifanger
 *
 */
public class Game extends Thread {
	/**
	 * Limite de jogadores por jogo.
	 */
	public static final int PLAYER_LIMIT	= 10;
	
	/**
	 * Tempo para cada sorteio do jogo.
	 */
	public static final int SORT_DELAY		= 5;
	
	/**
	 * Tempo da contagem regressiva em minutos.
	 */
	public static final int START_TIME		= 60;
	
	/**
	 * Tempo da contagem regressiva para iniciar o jogo. (em segundos)
	 */
	public static final int COUNT_DOWN_TIME	= 1;
	
	/**
	 * Tamanho m�ximo de uma cartela.
	 */
	public static final int MAX_CART_SIZE	= 24;
	
	/**
	 * Tempo entre um jogo e outro (em segundos)
	 */
	public static final int DELAY_BTW_GAMES	= 10;
	
	/**
	 * Lista com todos os jogadores.
	 */
	private ArrayList<PlayerHandler> playerList;
	
	/**
	 * Armazena o tempo da contagem regressiva.
	 */
	private int currentCountDownTime = 0;
	
	/**
	 * Indica se o jogo est� iniciado ou n�o.
	 */
	private boolean started = false;
	
	/**
	 * Armazena os n�meros sorteados.
	 */
	private ArrayList<Integer> drawnNumbers = new ArrayList<Integer>();
	
	/**
	 * Verifica se algu�m j� pediu bingo.
	 */
	private Player bingo = null;
	
	/**
	 * Construtor padr�o. Requer a Thread do server, para comunica��o.
	 * @param server Server Thread.
	 */
	public Game(ServerThread server)
	{
		this.playerList = new ArrayList<PlayerHandler>();
	}
	
	@Override
	public void run()
	{
		startCountDown();
	}
	
	/**
	 * Inicia a contagem regressiva para in�cio do jogo.
	 */
	public void startCountDown()
	{
		this.currentCountDownTime = 0;
		while(currentCountDownTime < START_TIME)
		{
			try {
				Thread.sleep(1000 * COUNT_DOWN_TIME);
				int rtime = (START_TIME - currentCountDownTime);
				broadcastPacket("M/O jogo come�a em " + rtime + " segundo" + ((rtime == 1) ? "" : "s") + ".");
				currentCountDownTime++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.startGame();
	}
	
	/**
	 * Inicia o jogo.
	 */
	public void startGame()
	{
		if(this.started)
		{
			System.out.println("Tentativa de iniciar um jogo já iniciado.");
			return;
		}
		
		// n�o h� jogadores, ent�o nem inicia
		if(this.playerList.size() == 0)
		{
			this.end();
			return;
		}
		
		this.started = true;
		
		// Thread respons�vel pelos sorteios
		BingoThread sortThread = new BingoThread(this);
		sortThread.start();
		
		broadcastPacket("M/Come�ando sorteio...");
	}
	
	/**
	 * Encerra o jogo e inicia outro ap�s o tempo determinado.
	 */
	public void end()
	{
		this.currentCountDownTime = START_TIME; // caso estivesse na contagem regressiva
		this.started = false;
		this.bingo = null;
		
		broadcastPacket("M/O jogo terminou!");
		System.out.println("Jogo terminado, recome�ando em " + DELAY_BTW_GAMES + " segundos.");
		
		try {
			Thread.sleep(1000 * DELAY_BTW_GAMES);
			System.out.println("Recome�ando...");
			startCountDown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Verifica se o jogo est� cheio ou n�o.
	 * @return 'true' se estiver cheio, 'false' n�o esteja cheio.
	 */
	public boolean isFull()
	{
		return this.playerList.size() >= Game.PLAYER_LIMIT;
	}
	
	/**
	 * Verifica se o jogo j� foi iniciado.
	 * @return 'true' se foi iniciado, 'false' caso contr�rio.
	 */
	public boolean isGameStarted()
	{
		return this.started;
	}
	
	/**
	 * Envia um pacote para todos os jogadores no jogo.
	 * @param packet Pacote de dados a ser enviado.
	 */
	public void broadcastPacket(String packet)
	{
		System.out.println(playerList + " => " + packet);
		for(PlayerHandler player : playerList)
		{
			player.sendMessage(packet);
		}
	}
	
	/**
	 * M�todo chamado quando um n�mero � sorteado pela BingoThread.
	 * @param sortedNumber N�mero sorteado.
	 */
	public void onNumberSorted(int sortedNumber)
	{
		this.broadcastPacket(String.format(GFProtocol.SORT_NUMBER, sortedNumber));
	}
	
	/**
	 * Retorna a lista de n�meros sorteados.
	 * @return Lista contendo n�meros sorteados.
	 */
	public ArrayList<Integer> getDrawnNumbers() {
		return drawnNumbers;
	}

	/**
	 * M�todo chamado quando um jogador se junta � partida.
	 * @param player Jogador que se juntou.
	 */
	public void onPlayerJoined(PlayerHandler player)
	{
		if(player == null)
			return;
		
		if(this.isGameStarted())
		{
			System.out.println(player.getName() + " tentou entrar em um jogo em andamento.");
			player.kick();
			return;
		}
		
		System.out.println(player.getName() + " entrou no jogo.");
		player.setCartela(new Cartela());
		playerList.add(player);
		player.sendMessage(String.format(GFProtocol.CARTELA, new Gson().toJson(player.getCartela())));
		broadcastPacket("M/" + player.getName() + " entrou.");
	}
	
	/**
	 * M�todo chamado quando um jogador pede Bingo.
	 * @param player Jogador que pediu bingo.
	 */
	public void onPlayerBingo(PlayerHandler player)
	{
		int pNumbers = 0;
		for(int n : player.getCartela().getCartela())
			if(this.drawnNumbers.contains(n))
				pNumbers++;
		
		if(pNumbers == MAX_CART_SIZE)
			this.onPlayerWon(player);
		else
			player.sendMessage("MB/Voc� marcou algum n�mero errado na cartela, pois nem todos os n�meros foram sorteados.");
	}
	
	/**
	 * M�todo chamado quando um jogador vence o jogo.
	 * @param player Jogador vitorioso.
	 */
	public void onPlayerWon(PlayerHandler player)
	{
		if(player == null)
			return;
		
		if(this.bingo != null)
		{
			player.sendMessage("MB/" + this.bingo + " j� pediu BINGO!");
			return;
		}
		
		this.bingo = player;
		this.bingo.setPassword(null);
		String playerString = "";
		
		try {
			playerString = new Gson().toJson(bingo, Player.class);
			System.out.println(playerString);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			Players.addWinCount(player);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.broadcastPacket(String.format(GFProtocol.END_GAME, playerString));
		this.broadcastPacket("MB/" + player.getName() + " GANHOU O JOGO!!!!!");
		this.end();
	}
	
	/**
	 * M�todo chamado quando um jogador deixa o jogo.
	 * @param player Jogador que saiu.
	 */
	public void onPlayerLeft(PlayerHandler player)
	{
		if(player == null)
			System.out.println("Um jogador desconhecido deixou o jogo.");
		else
		{
			System.out.println(player.getName() + " deixou o jogo.");
			this.playerList.remove(player);
			
			if(this.playerList.size() == 0)
			{
				System.out.println("Todos os jogadores sa�ram.");
				// Encerra o jogo for falta de jogadores
				this.end();
			}
		}
	}
}
