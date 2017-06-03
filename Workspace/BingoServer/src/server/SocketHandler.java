package server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class SocketHandler {
	
	protected Socket		socket		= null;
	protected PrintStream	printStream	= null;
	
	public SocketHandler(Socket socket)
	{
		if(socket == null)
		{
			System.out.println("O Socket � nulo.");
			return;
		}
		
		this.socket = socket;
		
		try
		{ this.printStream = new PrintStream(this.socket.getOutputStream()); }
		catch (IOException e)
		{ e.printStackTrace(); }
	}
	
	public void sendMessage(String message)
	{
		if(!this.socket.isConnected())
		{
			System.out.println("Falha ao enviar mensagem. O Socket n�o est� conectado.");
			return;
		}
		
		 if(this.printStream == null)
		 {
			 System.out.println("N�o foi poss�vel enviar mensagem ao destinat�rio. (PrintStream nula)");
			 return;
		 }
		 
		 this.printStream.println(message);
		 this.printStream.flush();
	}
	
	public void disconnect()
	{
		this.printStream.close();
		try { this.socket.close(); }
		catch(IOException e) {}
	}
	
}
