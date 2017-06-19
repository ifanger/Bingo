package threads;

import java.util.Scanner;

import gui.LoginWindow;
import protocol.GFProtocol;
import protocol.Player;
import protocol.Ranking;
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
					case GFProtocol.PacketType.RANKING:
						System.out.println(receivedPacket);
						Ranking ranking = GFProtocol.getRanking(receivedPacket);
						
						Player player1 = ranking.getFirstPlayer();
						Player player2 = ranking.getSecondPlayer();
						Player player3 = ranking.getThirdPlayer();
						
						this.login.getLblJogador1().setText(player1.getName());
						this.login.getLblJogador2().setText(player2.getName());
						this.login.getLblJogador3().setText(player3.getName());
						this.login.getLblVitoria1().setText(player1.getWinsCount() + "");
						this.login.getLblVitoria2().setText(player2.getWinsCount() + "");
						this.login.getLblVitoria3().setText(player3.getWinsCount() + "");
					default: // Pacote desconhecido
						System.out.println("Pacote desconhecido recebido: " + receivedPacket);
						break;
				}
			}
		}
		
	}
	
}
