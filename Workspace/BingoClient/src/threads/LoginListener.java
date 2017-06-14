package threads;

import java.util.Scanner;

import gui.LoginWindow;
import protocol.GFProtocol;
import protocol.Player;
import utils.Connection;

public class LoginListener extends Thread {
	private LoginWindow login;
	private Connection connection;
	
	public LoginListener(LoginWindow parent, Connection connection)
	{
		this.login = parent;
		this.connection = connection;
	}

	@Override
	public void run() {
		
		while(this.connection.isConnected())
		{
			Scanner scanner = this.connection.getInput();
			
			if(scanner.hasNextLine())
			{
				String receivedPacket = scanner.nextLine();
				int packetType = GFProtocol.getPacketType(receivedPacket);
				
				switch(packetType)
				{
					case GFProtocol.PacketType.LOGIN_F:
						Player receivedPlayer = GFProtocol.isLoggedIn(receivedPacket);
						if(receivedPlayer == null)
							login.onLoginFailed();
						else
							login.onLogginSuccess(receivedPlayer);
						break;
					default: // Pacote desconhecido
						System.out.println("Pacote desconhecido recebido: " + receivedPacket);
						break;
				}
			}
		}
		
	}
	
}
