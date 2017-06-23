package server;

import com.google.gson.Gson;

import protocol.Cartela;
import protocol.Player;
import threads.KeyboardThread;
import threads.ServerThread;

/**
 * Classe principal da aplica��o.
 * @author Gustavo Ifanger
 *
 */
public class Server {
	/**
	 * Porta na qual o servidor ser� executado.
	 */
	private static final int LISTEN_PORT	= 8090;
	
	public static void main(String args[])
	{
		try
		{
			Thread kbThread = new KeyboardThread();
			kbThread.start();
			
			ServerThread serverThread =
					new ServerThread(Server.LISTEN_PORT);
			serverThread.start();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}
}
