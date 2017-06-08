package server;

import threads.KeyboardThread;
import threads.ServerThread;

public class Server {
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
