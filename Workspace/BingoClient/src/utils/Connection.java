package utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Connection {
	private Socket socket;
	private PrintStream output;
	private BufferedReader input;
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

	public synchronized void sendPacket(String packet)
	{
		if(this.connected)
		{
			this.output.println(packet);
			this.output.flush();
		}
	}

	public Socket getSocket() {
		return socket;
	}

	public synchronized PrintStream getOutput() {
		return output;
	}

	public synchronized BufferedReader getInput() {
		return input;
	}

	public boolean isConnected() {
		return connected;
	}

	public synchronized void setConnected(boolean connected) {
		this.connected = connected;
	}
	
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
