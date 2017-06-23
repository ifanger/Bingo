package threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import daos.Players;
import protocol.Ranking;
import server.Game;
import server.SocketHandler;

/**
 * Thread responsável pela execução do servidor.
 * @author Gustavo Ifanger
 *
 */
public class ServerThread extends Thread {
	
	protected int 						serverPort		= 8090;
	protected ServerSocket				serverSocket	= null;
	protected boolean 					serverStopped	= false;
	protected Thread					runningThread	= null;
	protected ArrayList<ClientThread>	clientList		= null;
	
	/**
	 * Construtor padrão.
	 * @param port Porta de execução do servidor.
	 */
	public ServerThread(int port) {
		this.serverPort = port;
		this.clientList = new ArrayList<ClientThread>();
	}

	@Override
	public void run()
	{
		synchronized(this)
		{ this.runningThread = Thread.currentThread(); }
		
		// Inicializa o ServerSocket
		this.startServer();
		
		Game game = new Game(this);
		System.out.println("Aguardando conexões.");
		game.start();
		// Executa operações enquanto o servidor estiver vivo
		while(!serverStopped)
		{
			// Inicialização de um cliente
			Socket clientSocket = null;
			try
			{
				// Recebe um novo cliente
				clientSocket = this.serverSocket.accept();
				System.out.println("Nova conexão recebida.");
			} catch(IOException e)
			{
				// Verifica se o servidor parou
				if(isStopped())
				{
					System.out.println("Servidor parado.");
					return;
				}
				
				throw new RuntimeException("Erro ao aceitar conexão de cliente.", e);
			}
			
			// Se o cliente for genuíno, executa a Thread do mesmo
			if(clientSocket != null)
			{
				SocketHandler handler = new SocketHandler(clientSocket);
				
				Thread clientThread =
						new ClientThread(clientSocket, this.clientList, handler, game, null);
				
				// Adiciona o cliente na lista de clientes
				this.addClient((ClientThread) clientThread);
				clientThread.start();
			}
		}
		
		System.out.println("Servidor parou.");
	}
	
	/**
	 * Verifica se o servidor parou.
	 * @return Estado do servidor.
	 */
	private synchronized boolean isStopped()
	{
		return this.serverStopped;
	}
	
	/**
	 * Para o servidor.
	 */
	public synchronized void stopServer()
	{
		this.serverStopped = true;
		
		try
		{
			this.serverSocket.close();
		} catch(IOException e)
		{
			throw new RuntimeException("Falha ao encerrar servidor.", e);
		}
	}
	
	/**
	 * Inicia o servidor.
	 */
	private void startServer()
	{
		try
		{
			this.serverSocket = new ServerSocket(this.serverPort);
			System.out.println("Servidor iniciado na porta " + this.serverPort + ".");
		} catch(IOException e)
		{
			System.out.println("Não foi possível iniciar o servidor na porta " + this.serverPort + ".");
		}
	}
	
	/**
	 * Adiciona um novo cliente à lista.
	 * @param client Cliente à ser adicionado.
	 */
	private synchronized void addClient(ClientThread client)
	{
		this.clientList.add(client);
	}
}
