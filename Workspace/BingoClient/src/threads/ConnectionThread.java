package threads;

import java.io.IOException;
import java.net.Socket;

import gui.LoginWindow;
import utils.Connection;

public class ConnectionThread extends Thread {
	private LoginWindow loginWindow;
	private Connection connection;
	private LoginListener listener;
	
	public ConnectionThread(LoginWindow loginWindow, Connection connection, LoginListener listener) {
		this.loginWindow = loginWindow;
		this.connection = connection;
		this.listener = listener;
	}

	@Override
	public void run()
	{
		Socket socket = null;
		
		try {
			socket = new Socket("localhost", 8090);
		} catch(IOException e)
		{
			e.printStackTrace();
			this.loginWindow.showMessage("Não foi possível conectar-se ao servidor.");
		} finally
		{
			this.connection = new Connection(socket);
			this.listener = new LoginListener(loginWindow, this.connection);
			
			if(socket != null)
			{
				if(socket.isConnected())
					this.connection.setConnected(true);
				else
					this.connection.setConnected(false);
			} else {
				this.connection.setConnected(false);
			}
		}
	}
}
