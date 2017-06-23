package utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Classe responsável por armazenar grande parte das variáveis de conexão.
 * @author Gustavo Ifanger
 *
 */
public class Connection {
	/**
	 * Socket de conexão com o servidor.
	 */
	private Socket socket;
	
	/**
	 * Stream para envio de pacotes.
	 */
	private PrintStream output;
	
	/**
	 * Buffer para leitura de dados.
	 */
	private BufferedReader input;
	
	/**
	 * Armazena o estado atual da conexão.
	 */
	private boolean connected;
	
	public Connection(Socket socket) {
		this.socket = socket;
		
		if(socket != null)
		{
			this.connected = socket.isConnected();
			
			try {
				this.output = new PrintStream(socket.getOutputStream());
				this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Envia um pacote para o servidor.
	 * @param packet Pacote a ser enviado.
	 */
	public synchronized void sendPacket(String packet)
	{
		if(this.connected)
		{
			this.output.println(packet);
			this.output.flush();
		}
	}

	/**
	 * Retorna o socket da conexão.
	 * @return Socket conectado ao servidor.
	 */
	public Socket getSocket() {
		return socket;
	}

	/**
	 * Retorna a stream para comunicação com o servidor.
	 * @return Stream para comunicação com o servidor.
	 */
	public synchronized PrintStream getOutput() {
		return output;
	}

	/**
	 * Retorna o buffer para leitura do servidor.
	 * @return Buffer de leitura.
	 */
	public synchronized BufferedReader getInput() {
		return input;
	}

	/**
	 * Retorna o estado atual da conexão.
	 * @return Verdadeiro caso esteja conectado, falso caso contrário.
	 */
	public boolean isConnected() {
		return connected;
	}

	/**
	 * Altera o estado atual da conexão.
	 * @param connected Novo estado da conexão.
	 */
	public synchronized void setConnected(boolean connected) {
		this.connected = connected;
	}
	
	/**
	 * Encerra a conexão com o servidor.
	 */
	public void disconnect()
	{
		try {
			this.input.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.output.close();
		
		try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Connection [socket=" + socket + ", output=" + output + ", input=" + input + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((input == null) ? 0 : input.hashCode());
		result = prime * result + ((output == null) ? 0 : output.hashCode());
		result = prime * result + ((socket == null) ? 0 : socket.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Connection other = (Connection) obj;
		if (input == null) {
			if (other.input != null)
				return false;
		} else if (!input.equals(other.input))
			return false;
		if (output == null) {
			if (other.output != null)
				return false;
		} else if (!output.equals(other.output))
			return false;
		if (socket == null) {
			if (other.socket != null)
				return false;
		} else if (!socket.equals(other.socket))
			return false;
		return true;
	}
	
	
}
