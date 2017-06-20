package server;

import java.util.ArrayList;

import com.google.gson.Gson;

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
	public static final int START_TIME		= 10;
	
	/**
	 * Tempo da contagem regressiva para iniciar o jogo. (em segundos)
	 */
	public static final int COUNT_DOWN_TIME	= 60;
	
	/**
	 * Tamanho máximo de uma cartela.
	 */
	public static final int MAX_CART_SIZE	= 25;
	
	/**
	 * Tempo entre um jogo e outro (em segundos)
	 */
	public static final int DELAY_BTW_GAMES	= 300;
	
	/**
	 * Lista com todos os jogadores.
	 */
	private ArrayList<PlayerHandler> playerList;
	
	/**
	 * Armazena o tempo da contagem regressiva.
	 */
	private int currentCountDownTime = 0;
	
	/**
	 * Indica se o jogo está iniciado ou não.
	 */
	private boolean started = false;
	
	/**
	 * Armazena os números sorteados.
	 */
	private ArrayList<Integer> drawnNumbers = new ArrayList<Integer>();
	
	/**
	 * Construtor padrão. Requer a Thread do server, para comunicação.
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
	 * Inicia a contagem regressiva para início do jogo.
	 */
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
	
	/**
	 * Inicia o jogo.
	 */
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
	
	/**
	 * Encerra o jogo e inicia outro após o tempo determinado.
	 */
	public synchronized void end()
	{
		this.started = false;
		
		try {
			Thread.sleep(1000 * DELAY_BTW_GAMES);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Verifica se o jogo está cheio ou não.
	 * @return 'true' se estiver cheio, 'false' não esteja cheio.
	 */
	public synchronized boolean isFull()
	{
		return this.playerList.size() >= Game.PLAYER_LIMIT;
	}
	
	/**
	 * Verifica se o jogo já foi iniciado.
	 * @return 'true' se foi iniciado, 'false' caso contrário.
	 */
	public synchronized boolean isGameStarted()
	{
		return this.started;
	}
	
	/**
	 * Envia um pacote para todos os jogadores no jogo.
	 * @param packet Pacote de dados a ser enviado.
	 */
	public synchronized void broadcastPacket(String packet)
	{
		for(PlayerHandler player : playerList)
			player.sendMessage(packet);
	}
	
	/**
	 * Método chamado quando um número é sorteado pela BingoThread.
	 * @param sortedNumber Número sorteado.
	 */
	public synchronized void onNumberSorted(int sortedNumber)
	{
		this.broadcastPacket(String.format(GFProtocol.SORT_NUMBER, sortedNumber));
	}
	
	/**
	 * Retorna a lista de números sorteados.
	 * @return Lista contendo números sorteados.
	 */
	public ArrayList<Integer> getDrawnNumbers() {
		return drawnNumbers;
	}

	/**
	 * Método chamado quando um jogador se junta à partida.
	 * @param player Jogador que se juntou.
	 */
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
	
	/**
	 * Método chamado quando um jogador pede Bingo.
	 * @param player Jogador que pediu bingo.
	 */
	public synchronized void onPlayerBingo(PlayerHandler player)
	{
		int pNumbers = 0;
		for(int n : player.getCartela().getCartela())
			if(this.drawnNumbers.contains(n))
				pNumbers++;
		
		if(pNumbers == MAX_CART_SIZE)
			this.onPlayerWon(player);
	}
	
	/**
	 * Método chamado quando um jogador vence o jogo.
	 * @param player Jogador vitorioso.
	 */
	public synchronized void onPlayerWon(PlayerHandler player)
	{
		String playerString = new Gson().toJson((Player) player);
		
		this.broadcastPacket(String.format(GFProtocol.END_GAME, playerString));
		this.end();
	}
}
