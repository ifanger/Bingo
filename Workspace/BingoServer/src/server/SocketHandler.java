package server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Classe responsável pelo manuseio do Socket.
 * @author Gustavo Ifanger
 *
 */
public class SocketHandler {
	
	protected Socket		socket		= null;
	protected PrintStream	printStream	= null;
	
	/**
	 * Construtor padrão.
	 * @param socket Socket a ser manuseado.
	 */
	public SocketHandler(Socket socket)
	{
		if(socket == null)
		{
			System.out.println("O Socket é nulo.");
			return;
		}
		
		this.socket = socket;
		
		try
		{ this.printStream = new PrintStream(this.socket.getOutputStream()); }
		catch (IOException e)
		{ e.printStackTrace(); }
	}
	
	/**
	 * Envia mensagem através do socket.
	 * @param message Mensagem a ser enviada.
	 */
	public void sendMessage(String message)
	{
		if(!this.socket.isConnected())
		{
			System.out.println("Falha ao enviar mensagem. O Socket não está conectado.");
			return;
		}
		
		 if(this.printStream == null)
		 {
			 System.out.println("Não foi possível enviar mensagem ao destinatário. (PrintStream nula)");
			 return;
		 }
		 
		 this.printStream.println(message);
		 this.printStream.flush();
	}
	
	/**
	 * Encerra o envio de dados.
	 */
	public void disconnect()
	{
		this.printStream.close();
	}
	
}
