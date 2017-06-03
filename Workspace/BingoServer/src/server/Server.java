package server;

import com.google.gson.Gson;

import daos.Players;
import protocol.Ranking;
import threads.ServerThread;

public class Server {
	private static final int LISTEN_PORT	= 8090;
	
	public static void main(String args[])
	{
		try
		{
			ServerThread serverThread =
					new ServerThread(Server.LISTEN_PORT);
			
			serverThread.start();
		} catch(Exception e)
		{
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}
}
